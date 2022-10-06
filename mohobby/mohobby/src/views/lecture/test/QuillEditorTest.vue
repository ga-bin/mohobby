<template>
  <div>
    <ClassSidebar></ClassSidebar>
    <router-link to="/classmain" class="btn btn-info btn-sm mr-2">홈으로</router-link>
    <div class="example">
      <quill-editor
        class="editor"
        ref="myTextEditor"
        :disabled="false"
        :value="content"
        :options="editorOption"
        @change="onEditorChange"
        @blur="onEditorBlur($event)"
        @focus="onEditorFocus($event)"
        @ready="onEditorReady($event)"
      />

      <details>
        <summary>미리보기</summary>
        <div class="output ql-snow">
          <div v-html="content"></div>
        </div>
      </details>

      <button @click="saveEditor">saveEditor</button>
  </div>
  </div>
</template>
<script>
import hljs from "highlight.js";
import debounce from "lodash/debounce";
import { quillEditor } from "vue-quill-editor";

// highlight.js style
import "highlight.js/styles/tomorrow.css";

// import theme style
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import axios from "axios";
import ClassSidebar from "@/components/lecture/ClassSidebar.vue";

export default {
  name: "quill-example-snow",
  title: "Theme: snow",
  components: {
    quillEditor,
    ClassSidebar
},
  data() {
    return {
      editorOption: {
        placeholder: "place holder test",
        modules: {
          toolbar: [
            ["bold", "italic", "underline", "strike"], // <strong>, <em>, <u>, <s>
            [{ header: 1 }, { header: 2 }], // <h1>, <h2>
            [{ size: ["small", false, "large", "huge"] }], //class 제어 - html로 되도록 확인
            [{ header: [1, 2, 3, 4, 5, 6, false] }], // <h1>, <h2>, <h3>, <h4>, <h5>, <h6>, normal
            [{ font: [] }], // 글꼴 class로 제어
            [{ color: [] }, { background: [] }], //style="color: rgb(230, 0, 0);", style="background-color: rgb(230, 0, 0);"
            ["image"]
          ],
          syntax: {
            highlight: (text) => hljs.highlightAuto(text).value,
          },
        },
      },
      content: "",
      sampleData: ""
    };
  },
  setup() {},
  created() {},
  mounted: function () {
    console.log("this is Quill instance:", this.editor);
  },
  unmounted() {},
  methods: {
    onEditorChange: debounce(function(value) {
      this.content = value.html;
    }, 466),
    onEditorBlur(editor) {
      console.log("editor blur!", editor);
    },
    onEditorFocus(editor) {
      console.log("editor focus!", editor);
    },
    onEditorReady(editor) {
      console.log("editor ready!", editor);
    },
    saveEditor() {
      axios({
        method: "POST",
        url: "http://localhost:8088/java/saveClassInfo",
        headers: {
          "Content-Type": "application/json; charset=utf-8",
        },
        timeout: 5000,
        data: JSON.stringify({
          content: this.content,
          filename: '2'
        })
      }).catch((error) => {
          console.log(`error: ${error}`);
      })
    }
  },
  computed: {
    editor() {
      return this.$refs.myTextEditor.quill;
    },
    contentCode() {
      return hljs.highlightAuto(this.content).value;
    },
  }
};
</script>

<style scoped>
  form {
    display: inline-block;
  }
  .authBox {
    padding: 20px;
    width: 400px;
    text-align: left;
    border: 1px solid gray;
  }
  .tokenBox {
    padding: 20px;
    width: 400px;
    text-align: left;
    border: 1px solid gray;
  }
  button {
    border: 1px solid gray;
  }
  input[type=submit] {
    border: 1px solid gray;
  }
</style>