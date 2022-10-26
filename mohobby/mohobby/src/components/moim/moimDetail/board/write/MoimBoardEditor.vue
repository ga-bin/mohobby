<template>
  <div class="container">
    <div class="flex">
      <v-chip class="ma-2 mt-4" text-color="white" color="black" label>목록</v-chip>
      <v-radio-group v-model="boardType" row>
      <v-radio class="ml-7" label="공지" value=0></v-radio>
      <v-radio label="메인글" value=1></v-radio>
    </v-radio-group>
    </div>
    <div class="flex">
      <v-chip class="ma-2 mt-4" text-color="white" color="black" label>제목</v-chip>
      <v-col cols="12" sm="6" md="11">
      <v-text-field class="ml-4" label="제목을 입력해주세요!" v-model="title" solo dense></v-text-field>
          </v-col>
    </div>
    <div class="example">
      <QuillEditor
                @editorEmit="getContent"
                ref="editor_content"
              ></QuillEditor>
  </div>
  <div class="center">
      <v-btn>취소</v-btn>
      <v-btn color="success" class="ml-5" @click="testEditor()">작성완료</v-btn>
    </div>
  </div>
</template>
-
<script>
import QuillEditor from "@/components/common/TextEditor.vue";
  
  export default {
    name: "quill-example-snow",
    title: "Theme: snow",
      components: {
    QuillEditor,
  },
    data() {
      return {
        styleEditor : {
          height : "300px"
        },
        editorOption: {
          placeholder: "내용을 입력해주세요",
          modules: {
            toolbar: [
              ["bold", "italic", "underline", "strike"], // <strong>, <em>, <u>, <s>
              ["blockquote", "code-block"], // <blockquote>, <pre class="ql-syntax" spellcheck="false">
              [{ header: 1 }, { header: 2 }], // <h1>, <h2>
              [{ list: "ordered" }, { list: "bullet" }],
              [{ script: "sub" }, { script: "super" }], // <sub>, <sup>
              [{ indent: "-1" }, { indent: "+1" }], // class제어
              [{ direction: "rtl" }], //class 제어
              [{ size: ["small", false, "large", "huge"] }], //class 제어 - html로 되도록 확인
              [{ header: [1, 2, 3, 4, 5, 6, false] }], // <h1>, <h2>, <h3>, <h4>, <h5>, <h6>, normal
              [{ font: [] }], // 글꼴 class로 제어
              [{ color: [] }, { background: [] }], //style="color: rgb(230, 0, 0);", style="background-color: rgb(230, 0, 0);"
              [{ align: [] }], // class
              // ["clean"],
              ["link", "image", "video"],
            ],
            syntax: {
              highlight: (text) => hljs.highlightAuto(text).value,
            },
          },
        },
        content: "",
        boardType: null,
        title : '',
        moimId : this.$route.query.moimId,
        boardId : ''
      };
    },
    methods: {
      getContent(editorContent) {
      console.log("emit_success", editorContent);
      this.content = editorContent;
      console.log("겟 콘탠트 내용", this.content);
    },
      async clickSave(boardId) {
      document.querySelector(".ql-editor").style.display ='none';

      let step = 0;

      let len = document.querySelector(".ql-editor").querySelectorAll("img").length;
      if(len != 0 ) {
        for(let i = 0; i < len; i++) {
          let src = document.querySelector(".ql-editor").getElementsByTagName("img")[i].src;
          step = await this.uploadImage(boardId, i, src, step);
        }
      }     
        this.saveEditor(boardId); 
    },
    testEditor() {
        console.log(this.title)
        console.log(this.boardType)
        console.log(this.$store.state.id)
        console.log(this.moimId)
      this.axios.post("/insertBoard", {
        title : this.title,
        boardType : this.boardType,
        memberId : this.$store.state.id,
        moimId : this.moimId
      })
      .then((resp) => {
        console.log(resp)
        console.log("data =" + resp.data)
        this.boardId = resp.data
        this.clickSave(this.boardId)
      })
      
    },
    saveEditor(boardId) {
      this.axios({
        method: "POST",
        url: "/saveMoimInfo",
        headers: {
          "Content-Type": "application/json; charset=utf-8",
        },
        timeout: 5000,
        data: JSON.stringify({
          content: this.content,
          filename: boardId
        })
      }).then((res) => {
        if(res.status == 200) {
          this.content = '';
          document.querySelector(".ql-editor").style.display ='block';
        }
        this.$router.push({ name : "moimBoard" });
      }).catch((error) => {
          console.log(`error: ${error}`);
      })
    },
    async uploadImage(folder, file, img, step) {   //폴더이름은 pk, 파일이름은 index로 
      let result = step;

      let res = await this.axios.post('/uploadMoimImage', {
        foldername: folder,
        filename: folder + "-" +  file,
        src: img
      })
    }
    },
    computed: {
      contentCode() {
        return hljs.highlightAuto(this.content).value;
      },
    },
    mounted() {
    },
  };
  </script>

<style scoped>
  .container {
    width: 85%;
  }
  .center {
      margin-top: 100px;
      display: flex;
      justify-content : center;
    }

    .flex {
    display : flex;
  }

</style>