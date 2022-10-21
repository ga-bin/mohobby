<template>
  <form id="classInput" name="classInput">
    <div class="container">
      <ClassSidebar></ClassSidebar>
      <h1>강의 개설 신청</h1>
      <br />
      <br />
      <div class="contents-box">
        <div class="contents">
          <br />
          <!-- 1/7 -->
          <span class="no">1/7</span>
          <h2>클래스에 대한 간략한 정보를 남겨 주세요</h2>
          <br />
          <!-- className -->
          <span class="label">클래스 명</span>
          <input
            type="text"
            class="input"
            placeholder="예) 수학 강의, 고양이 교실, 편집 강의, 텃밭 가꾸기"
            name="className"
            v-model="className"
          />
          <!-- classType -->
          <span class="label">클래스 방식</span>
          <v-radio-group v-model="classType" row name="classType">
            <v-radio
              style="margin-right: 50px"
              label="ONLINE"
              value="0"
            ></v-radio>
            <v-radio label="OFFLINE" value="1"></v-radio>
          </v-radio-group>
          <!-- region -->
          <span class="label">지역</span>
          <v-radio-group row v-model="regionId" name="regionId">
            <div v-for="(item, index) in regionList" :key="index">
              <v-radio
                style="margin-right: 50px"
                :label="item.keywordName"
                :value="item.keywordId"
              ></v-radio>
            </div>
          </v-radio-group>
          <!-- jobName -->
          <span class="label">직업</span>
          <input type="text" class="input" name="jobName" v-model="jobName" />
          <!-- keywordName(카테고리이름)  -->
          <span class="label">재주 분야</span>
          <v-radio-group
            row
            v-model="selectedKeywordId"
            name="selectedKeywordId"
          >
            <div v-for="(item, index) in catg" :key="index">
              <v-radio
                style="margin-right: 50px"
                :label="item.keywordName"
                :value="item.keywordId"
              ></v-radio>
            </div>
          </v-radio-group>
          <!-- 금액-->
          <span class="label">금액(VAT(포함)</span>
          <input
            type="text"
            class="input"
            placeholder="숫자만입력"
            style="width: 1150px; margin-right: 10px"
            name="classPrice"
            v-model="classPrice"
          />
          <span style="font-size: 20px; margin-right: 10px">원</span>
          <!-- 수료증 발급가능-->
          <span class="label">수료증 발급 가능 여부</span>
          <v-radio-group v-model="certAble" row name="certAble">
            <v-radio
              style="margin-right: 50px"
              label="가능"
              value="1"
            ></v-radio>
            <v-radio label="불가능" value="0"></v-radio>
          </v-radio-group>
          <span class="label">수료증 발급 기준</span>
          <input
            type="text"
            class="input"
            placeholder="숫자만입력"
            style="width: 1150px; margin-right: 10px"
            name="certStandard "
            v-model="certStandard"
          />
        </div>
      </div>
      <br />
      <br />
      <div class="contents-box">
        <div class="contents">
          <br />
          <span class="no">2/7</span>
          <h2>전문가 인증을 해주세요</h2>
          <br />
          <span class="label" style="display: inline-block; margin-right: 85px"
            >실명</span
          >
          <input
            type="text"
            class="input"
            placeholder="홍길동"
            style="width: 400px; margin: 0 10px 0 10px"
          />
          <span class="label" style="display: inline-block; margin-right: 25px"
            >주민등록번호</span
          >
          <input
            type="text"
            class="input"
            placeholder="숫자만 입력"
            style="width: 400px; margin: 0 10px 0 10px"
          />
          <button>인증</button>
          <br />
          <br />
          <span class="label" style="display: inline-block"
            >수익금 출금 은행</span
          >
          <select
            class="input"
            placeholder="홍길동"
            style="width: 400px; margin: 0 10px 0 10px"
            name="bankCode"
            v-model="bankCode"
          >
            <option value="red">붉은색</option>
            <option value="black">검은색</option>
            <option value="blue">파란색</option>
          </select>
          <span class="label" style="display: inline-block"
            >수익금 출금 계좌</span
          >
          <input
            type="text"
            class="input"
            placeholder="숫자만 입력"
            style="width: 400px; margin: 0 10px 0 10px"
            name="account"
            v-model="account"
          />
          <button>인증</button>
          <br />
          <br />
        </div>
      </div>
      <br />
      <br />
      <div class="contents-box">
        <div class="contents">
          <span class="no">3/8</span>
          <h2>강사의 개인 정보를 입력해 주세요</h2>
        </div>
      </div>
      <br />
      <br />
      <div class="contents-box">
        <div class="contents">
          <br />
          <span class="no">3/7</span>
          <h2>클래스를 설명해 주세요.</h2>
          <br />
          <!-- 에디터 -->
          <div class="example" style="width: 1300px" name="classInfo">
            <quill-editor
              class="editor"
              style="height: 500px"
              ref="myTextEditor"
              :disabled="false"
              :value="content"
              :options="editorOption"
              @change="onEditorChange"
              @blur="onEditorBlur($event)"
              @focus="onEditorFocus($event)"
              @ready="onEditorReady($event)"
              name="classInfo"
            />
            <br />
            <br />
            <details>
              <summary>미리보기</summary>
              <div class="output ql-snow">
                <div v-html="content"></div>
              </div>
            </details>
            <button @click="clickSave">saveEditor</button>
            <br />
            <br />
          </div>
        </div>
      </div>
      <br />
      <br />
      <div class="contents-box">
        <div class="contents">
          <br />
          <span class="no">4/7</span>
          <h2>클래스를 잘 나타낼 수 있도록 이미지를 등록하세요.</h2>
          <br />
          <span class="label">메인이미지등록(필수)</span>

          <!-- 파일등록부 -->
          <v-file-input
            class="mx-auto"
            label="이미지 파일을 등록해주세요!(jpg,png,jpeg 형식만 가능)"
            type="file"
            filled
            prepend-icon="mdi-camera"
            counter
            show-size
            dense
            multiple
            @change="mainImageChange"
            name="mainFileList"
            accept="image/*"
          />
          <!-- 파일이름, 개수 -->
          <div v-for="(list, i) in mainFileList" :key="i">
            {{ list.name }}
          </div>

          <!-- 이미지 미리보기 -->
          <div style="display: inline-flex; margin-left: 10px">
            <v-img
              v-for="(item, i) in mainUploadimageurl"
              :key="i"
              :src="item.url"
              aspect-ratio="4/3"
              height="150px"
              width="200px"
              lazy-src
              error
              style="margin-right: 10px"
            />
          </div>
          <span class="label">추가이미지등록(선택)</span>
          <!-- 파일등록부 -->
          <v-file-input
            class="mx-auto"
            label="이미지 파일을 등록해주세요!(jpg,png,jpeg 형식만 가능)"
            type="file"
            filled
            prepend-icon="mdi-camera"
            counter
            show-size
            dense
            multiple
            @change="subImageChange"
            name="subFileList"
            accept="image/*"
          />
          <!-- 파일이름, 개수 -->
          <div v-for="(list, i) in subFileList" :key="i">
            {{ list.name }}
          </div>

          <!-- 이미지 미리보기 -->
          <div style="display: inline-flex; margin-left: 10px">
            <v-img
              v-for="(item, i) in subUploadimageurl"
              :key="i"
              :src="item.url"
              aspect-ratio="4/3"
              height="150px"
              width="200px"
              lazy-src
              error
              style="margin-right: 10px"
            />
          </div>
        </div>
      </div>
      <br />
      <br />
      <div class="contents-box">
        <div class="contents">
          <br />
          <span class="no">5/7</span>
          <h2>커리큘럼을 등록하세요</h2>
          <br />
          <span class="label">챕터를 등록하세요</span>
          <div>
            <span
              style="
                padding: 10px 15px 10px 15px;
                display: inline-block;
                border: 1px solid black;
                margin-right: 20px;
              "
              >1</span
            >
            <input
              class="input"
              placeholder="챕터 제목을 입력하세요"
              style="width: 1100px"
            />
            <button
              style="margin: 0px 20px 0px; border: 1px solid red; color: red"
            >
              -
            </button>
            <button style="border: 1px solid green; color: green">+</button>
          </div>
          <span class="label">회차를 등록하세요</span>
          <div>
            <span
              style="
                padding: 10px 15px 10px 15px;
                display: inline-block;
                border: 1px solid black;
                margin-right: 20px;
              "
              >1</span
            >
            <select
              class="input"
              style="width: 450px; margin: 0 20px 0 10px"
              name="color2"
            >
              <option value="red">붉은색</option>
              <option value="black">검은색</option>
              <option value="blue">파란색</option>
            </select>
            <input
              class="input"
              placeholder="강의 제목을 입력하세요"
              style="width: 620px"
            />
            <button
              style="margin: 0px 20px 0px; border: 1px solid red; color: red"
            >
              -
            </button>
            <button style="border: 1px solid green; color: green">+</button>
          </div>
        </div>
      </div>
      <br />
      <br />
      <div class="contents-box">
        <div class="contents">
          <br />
          <span class="no">5/7</span>
          <h2>수업에 필요한 자료를 등록하세요.</h2>
          <br />
          <span style="display: inline-block" class="label">회차</span>
          <span style="margin-left: 30px; display: inline-block" class="label"
            >영상 등록</span
          >
          <span style="margin-left: 490px; display: inline-block" class="label"
            >교안 등록</span
          >
          <div>
            <span
              style="
                padding: 10px 15px 10px 15px;
                display: inline-block;
                border: 1px solid black;
                margin-right: 20px;
              "
              >1</span
            >
            <input
              class="input"
              placeholder="챕터 제목을 입력하세요"
              style="width: 530px; display: inline-block"
            />
            <input
              type="file"
              class="input"
              style="width: 540px; margin-left: 20px"
              accept="image/jpeg,.txt,.mp4"
            />
            <button
              style="margin: 0px 20px 0px; border: 1px solid red; color: red"
            >
              -
            </button>
            <button style="border: 1px solid green; color: green">+</button>
          </div>
        </div>
      </div>
      <br />
      <br />
      <div class="contents-box">
        <div class="contents">
          <br />
          <span class="no">7/7</span>
          <h2>수업을 듣기 전 필요한 준비물을 등록하세요</h2>
          <span class="label"
            >필요한 물품만 등록해주면 판매 연계는 모하비가 도와드립니다!</span
          >
          <br />
          <br />
          <span class="label">준비물을 등록하세요.</span>
          <input
            type="text"
            class="input"
            placeholder="준비물을 입력하세요"
            style="width: 1150px; margin-right: 10px"
          />
        </div>
      </div>
      <br />
      <br />
      <button
        style="
          border: none;
          background-color: lightgrey;
          width: 1350px;
          font-weight: bold;
        "
      >
        신청
      </button>
      <br />
      <br />
      <br />
      <br />
      <br />
    </div>
  </form>
</template>
<script>
import ClassSidebar from "@/components/lecture/ClassSidebar.vue";
// editor
import hljs from "highlight.js";
import debounce from "lodash/debounce";
import { quillEditor } from "vue-quill-editor";

// highlight.js style
import "highlight.js/styles/tomorrow.css";

// import theme style
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";

export default {
  name: "",
  components: {
    ClassSidebar,
    quillEditor,
  },
  data() {
    return {
      sampleData: "",
      phoneNum: "",
      catg: [],
      keywordCount: 0,
      regionList: [],
      selectedKeywordId: "",
      regionId: "",
      memberId: "",
      className: "",
      classPrice: 0,
      classInfo: "",
      imgAmount: 0,
      classType: 0,
      certAble: 0,
      certStandard: "",
      bankCode: "",
      account: "",
      postcode: "",
      address: "",
      addressDetail: "",
      startDate: 0,
      accountHolder: "",
      // editor
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
            ["image"],
          ],
        },
      },
      //이미지Data
      mainUploadimageurl: [], //미리보기 이미지url
      subUploadimageurl: [], //미리보기 이미지url
      imagecnt: 0, //업로드한 이미지개수 axious시에 넘겨줌
      subFileList: [],
      mainFileList: [],
      file: {},
      formData: {},
      content: "",
      sampleData: "",
      imgList: [],
      files: [],
    };
  },
  setup() {},
  created() {},
  mounted() {
    this.getAllCatg();
    this.getAllRegion();
  },
  computed: {
    editor() {
      return this.$refs.myTextEditor.quill;
    },
    contentCode() {
      return hljs.highlightAuto(this.content).value;
    },
  },
  unmounted() {},
  methods: {
    // 지역 전체 가져오기
    getAllRegion() {
      const vm = this;
      this.axios({
        url: "http://localhost:8088/java/regionAll",
        method: "get",
      })
        .then(function (response) {
          console.log(response);
          if (response.data != "") {
            console.log(response.data);
            vm.regionList = response.data;
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    // 관심사 전체 목록 가져오기
    getAllCatg() {
      const vm = this;
      this.axios({
        url: "http://localhost:8088/java/allCatg",
        method: "get",
      })
        .then(function (response) {
          console.log(response.data);
          if (response.data != "") {
            console.log(vm.catg);
            vm.catg = response.data;
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    // 에디터
    onEditorChange: debounce(function (value) {
      this.content = value.html;
    }, 466),
    onEditorBlur(editor) {
      //console.log("editor blur!", editor);
    },
    onEditorFocus(editor) {
      //console.log("editor focus!", editor);
    },
    onEditorReady(editor) {
      //console.log("editor ready!", editor);
    },
    async clickSave() {
      document.querySelector(".ql-editor").style.display = "none";

      let step = 0;

      let len = document
        .querySelector(".ql-editor")
        .querySelectorAll("img").length;
      let classId = 1;
      if (len != 0) {
        for (let i = 0; i < len; i++) {
          let src = document
            .querySelector(".ql-editor")
            .getElementsByTagName("img")[i].src;
          step = await this.uploadImage(classId, i, src, step);
        }
      }

      if (step == len) {
        this.saveEditor();
      }
    },
    saveEditor() {
      this.axios({
        method: "POST",
        url: "/saveClassInfo",
        headers: {
          "Content-Type": "application/json; charset=utf-8",
        },
        timeout: 5000,
        data: JSON.stringify({
          content: this.content,
          filename: "1",
        }),
      })
        .then((res) => {
          if (res.status == 200) {
            this.content = "";
            document.querySelector(".ql-editor").style.display = "block";
          }
        })
        .catch((error) => {
          console.log(`error: ${error}`);
        });
    },
    async uploadImage(folder, file, img, step) {
      //폴더이름은 pk, 파일이름은 index로
      let result = step;

      let res = await this.axios.post("/uploadClassImage", {
        foldername: folder,
        filename: file,
        src: img,
      });

      if (res.status == 200) {
        // let html = `<img :src="require(`+"`"+`@/assets/image/class/info/${folder}/${file}.jpg`+"`"+`)">`;
        // document.querySelector(".ql-editor").getElementsByTagName("img")[file].outerHTML = html;
        // this.content = document.querySelector(".ql-editor").innerHTML;

        result = step + 1;
      }
      return result;
    },

    //이미지 미리보기
    mainImageChange(file) {
      // v-file-input @OnChange
      if (!file) return;
      file.forEach((getFile) => {
        //파일등 정보 forEach문으로 빼오기
        console.log("item.name: " + getFile.name); //name:파일명, size:바이트(인듯),type:(image/)png
        const fileReader = new FileReader(); //파일리더기 생성
        fileReader.onload = (e) => {
          //파일 성공적으로 읽어오면 이벤트 실행
          this.mainUploadimageurl.push({ url: e.target.result }); // e.target.result를 통해 이미지 url을 가져와서 uploadimageurl에 저장
          if (this.mainUploadimageurl.length > 1) {
            this.mainUploadimageurl.shift();
          }
          console.log({ url: e.target.result });
        };
        fileReader.readAsDataURL(getFile); //바이너리 파일을 Base64 Encode 문자열로 반환 Ex.) data:image/jpeg; base64, ….
      });
    },
    //이미지 미리보기
    subImageChange(file) {
      // v-file-input @OnChange
      this.subUploadimageurl = [];
      if (!file) return;

      file.forEach((getFile) => {
        //파일등 정보 forEach문으로 빼오기
        console.log("item.name: " + getFile.name); //name:파일명, size:바이트(인듯),type:(image/)png
        const fileReader = new FileReader(); //파일리더기 생성
        fileReader.onload = (e) => {
          //파일 성공적으로 읽어오면 이벤트 실행
          this.subUploadimageurl.push({ url: e.target.result }); // e.target.result를 통해 이미지 url을 가져와서 uploadimageurl에 저장
          console.log({ url: e.target.result });
        };
        fileReader.readAsDataURL(getFile); //바이너리 파일을 Base64 Encode 문자열로 반환 Ex.) data:image/jpeg; base64, ….
      });
    },
    //게시글 등록
    //미리보기에서 사진 삭제돼야함 ->
    uploadImage() {
      const formData = new FormData(classInput); // 파일을 전송할때는 FormData 형식으로 전송
      this.axios
        .post("/sns/myfeed", formData, {
          // 게시글 저장
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then(function (res) {
          console.log("게시글저장 성공!");
          // this.goUserFeed(this.memberId);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
.container {
  margin-left: 40px;
}

.contents-box {
  width: 1350px;
  border: 0.5px solid grey;
}

.contents {
  margin-left: 20px;
}

.no {
  color: #2ac187;
  font-size: 20px;
}

.label {
  font-size: 17px;
  display: block;
}

.input {
  padding: 10px;
  width: 1200px;
  border: 1px solid lightgrey;
  margin-top: 10px;
  margin-bottom: 25px;
}

button {
  border: 1px solid black;
  border-radius: 5px;
  padding: 10px 17px 10px 17px;
}

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

.v-input {
  margin: 0px;
}
</style>
