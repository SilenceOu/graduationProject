<template>
  <div>
    <div style="height: 300px;width: 700px;z-index: 9999;">
      <quill-editor
              style="height: 100%;width: 100%;"
              :ref="nodeName"
              v-model="content"
              :options="editorOption"
              @blur="onEditorBlur($event)"
              @focus="onEditorFocus($event)"
              @ready="onEditorReady($event)"
              @change="onEditorChange($event)"
      ></quill-editor>
    </div>
    <input type="file" ref="imgInput" @change="onChange($event)" style="opacity: 0">
  </div>
</template>

<script>
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";
import { quillEditor, Quill } from "vue-quill-editor";

const Font = Quill.import("formats/font");
Font.whitelist = ["Arial", "宋体", "黑体", "微软雅黑"];
Quill.register(Font, true);
export default {
  name: "SiEditor",
  props: {
    editable: { type: Boolean, default: true },
    defaultContent: {
      type: String,
      default: ""
    },
    nodeName:{
      type:String,
      default:"myQuillEditor"
    }
  },
  data() {
    return {
      content: this.defaultContent,
      editor: null,
      editorOption: {
        modules: {
          toolbar: [
            ["bold", "italic", "underline", "strike"],
            ["blockquote", "code-block"],
            [{ header: 1 }, { header: 2 }],
            [{ list: "ordered" }, { list: "bullet" }],
            [{ script: "sub" }, { script: "super" }],
            [{ indent: "-1" }, { indent: "+1" }],
            [{ direction: "rtl" }],
            [{ size: ["small", false, "large", "huge"] }],
            [{ header: [1, 2, 3, 4, 5, 6, false] }],
            [
              {
                font: ["Arial", "宋体", "黑体", "微软雅黑"]
              }
            ],
            [{ color: [] }, { background: [] }],
            [{ align: [] }],
            ["clean"],
            ["link", "image", "video"]
          ],
          syntax: {
            highlight: text => hljs.highlightAuto(text).value
          }
        },
        placeholder: "请输入"
      }
    };
  },
  components: {
    quillEditor
  },
  mounted(){

  },
  watch: {
    editable(flag) {
      this.editor.enable(flag);
    },
    defaultContent(content) {
      this.content = content;
    }
  },
  methods: {
    onEditorBlur(editor) {},
    onEditorFocus(editor) {},
    onEditorReady(editor) {
      this.editor = editor;
      this.$on("on-form-submit", () => {
        this.$emit("on-transport-editor-content", this.content);
      });
    },
    onEditorChange(editor) {
      console.log(editor);
      this.$emit('on-change-content',editor);
    },
    // 点击图片ICON触发事件
    imgHandler(state) {
      console.log(state)
      this.addRange = this.$refs[this.nodeName].quill.getSelection()
      if (state) {

        let fileInput = this.$refs.imgInput;
        fileInput.click() // 加一个触发事件
      }
    },
    onChange(ev){
      let file = ev.target.files[0];
      let param = new FormData();
      param.append('file',file);

      this.$post('/upload/headImage',param,res => {

          let url = res.data.data;
          this.content = this.$refs.myQuillEditor.quill.getSelection();
          // 调用编辑器的 insertEmbed 方法，插入URL
          this.$refs.myQuillEditor.quill.insertEmbed(this.content !== null ? this.content.index : 0, 'image', url, Quill.sources.USER)
          console.log(url);
          console.log(this.content);

      })

    }
  }
};
</script>

<style lang="less" scoped>
  #imgInput{
    opacity: 0;
  }
</style>
