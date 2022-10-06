<template>
  <div>
    <ClassSidebar></ClassSidebar>
    <button @click="clearSession">clearSession</button><br/>
    <div class="authBox">
      <h3>사용자 인증</h3>
      <span>code: {{ auth.code }}</span><br/>
      <span>scope: {{ auth.scope }}</span><br/>
      <span>client_info: {{ auth.client_info }}</span><br/>
      <span>state: {{ auth.state }}</span><br/><br/>
      <form method="get" action="https://testapi.openbanking.or.kr/oauth/2.0/authorize" target="_blank">
        <input type="hidden" name="response_type" value="code"/>
        <input type="hidden" name="client_id" value="09076cd0-82f3-4c5b-a982-862c4c655cca"/>
        <input type="hidden" name="redirect_uri" value="http://localhost:8088"/>
        <input type="hidden" name="scope" value="login inquiry transfer"/>
        <input type="hidden" name="state" value="b80BLsfigm9OokPTjy03elbJqRHOfGSY"/>
        <input type="hidden" name="auth_type" value="0"/>
        <input type="submit" value="requestAuth"/>
      </form>
      <button @click="saveAuth">saveAuth</button>
    </div>
    <div class="tokenBox">
      <h3>토큰발급</h3>
      <span>access_token: {{ token.access_token }}</span><br/>
      <span>expires_in: {{ token.expires_in }}</span><br/>
      <span>refresh_token: {{ token.refresh_token }}</span><br/>
      <span>scope: {{ token.scope }}</span><br/>
      <span>token_type: {{ token.token_type }}</span><br/>
      <span>user_seq_no: {{ token.user_seq_no }}</span><br/><br/>
      <button @click="requestToken">requestToken</button>
      <button @click="saveToken">saveToken</button>
    </div>
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
import ClassSidebar from "../../components/lecture/ClassSidebar.vue";
import hljs from "highlight.js";
import debounce from "lodash/debounce";
import { quillEditor } from "vue-quill-editor";

// highlight.js style
import "highlight.js/styles/tomorrow.css";

// import theme style
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import axios from "axios";

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
      sampleData: "",
      auth: {
        code: '5HafwwLPu3WJ2fSvI3XKtqmi1Y562O',
        scope: 'inquiry login transfer',
        client_info: 'test',
        state: 'b80BLsfigm9OokPTjy03elbJqRHOfGSY'
      },
      token: {
        access_token: '',
        expires_in: '',
        refresh_token: '',
        scope: '',
        token_type: '',
        user_seq_no: ''
      }
    };
  },
  setup() {},
  created() {},
  mounted: function () {
    this.init();
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
    init () {
      if (sessionStorage.auth) {
        this.auth = JSON.parse(sessionStorage.auth)
      }
      if (sessionStorage.token) {
        this.token = JSON.parse(sessionStorage.token)
      }
    },
    clearSession () {
      sessionStorage.auth = ''
      sessionStorage.token = ''
      this.auth = {
        code: '',
        scope: '',
        client_info: '',
        state: ''
      }
      this.token = {
        access_token: '',
        expires_in: '',
        refresh_token: '',
        scope: '',
        token_type: '',
        user_seq_no: ''
      }
    },
    saveAuth () {
      sessionStorage.auth = JSON.stringify({
        code: this.$route.query.code,
        scope: this.$route.query.scope,
        client_info: this.$route.query.client_info,
        state: this.$route.query.state
      })
      this.auth = JSON.parse(sessionStorage.auth)
    },
    requestToken () {
      axios('http://localhost:8088/java/requestToken', {
        method: "POST",
        headers: {
          "Content-Type": "application/json; charset=utf-8",
        },
        body: JSON.stringify({
          "client_id": "09076cd0-82f3-4c5b-a982-862c4c655cca",
          "client_secret": "43c3d1e6-c3dc-428c-8722-b6b388e20a35",
          "scope": "oob",
          "grant_type": "client_credentials"
        })
      }).then((response) => {
        console.log(result);
        if(response.ok) {
          return response.json();
        }
        throw new Error('Network response was not ok.');
      }).then((data) => {
        console.log(data);
        this.token = JSON.stringify(data);
      }).catch((error) => {
          console.log(`error: ${error}`);
      })
    },
    saveToken () {
      sessionStorage.token = JSON.stringify({
        access_token: this.token.access_token,
        expires_in: this.token.expires_in,
        refresh_token: this.token.refresh_token,
        scope: this.token.scope,
        token_type: this.token.token_type,
        user_seq_no: this.token.user_seq_no
      })
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
