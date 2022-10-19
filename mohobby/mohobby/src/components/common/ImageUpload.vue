<template>
  <form id="feedInsert" name="feedInsert">
    <v-container fluid>
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
        @change="onImageChange"
        name="fileList"
        accept="image/*"
      />
      <!-- 파일이름, 개수 -->
      <div v-for="(list, i) in fileList" :key="i">
        {{ list.name }}
      </div>

      <!-- 이미지 미리보기 -->
      <div style="display: inline-flex; margin-left: 10px">
        <v-img
          v-for="(item, i) in uploadimageurl"
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
    </v-container>
  </form>
</template>

<script>
export default {
  data() {
    return {
      //이미지Data
      uploadimageurl: [], //미리보기 이미지url
      imagecnt: 0, //업로드한 이미지개수 axious시에 넘겨줌
      fileList: [],
      file: {},
      formData: {},
    };
  },
  methods: {
    //이미지 미리보기
    onImageChange(file) {
      // v-file-input @OnChange
      if (!file) return;

      file.forEach((getFile) => {
        //파일등 정보 forEach문으로 빼오기
        console.log("item.name: " + getFile.name); //name:파일명, size:바이트(인듯),type:(image/)png
        const fileReader = new FileReader(); //파일리더기 생성
        fileReader.onload = (e) => {
          //파일 성공적으로 읽어오면 이벤트 실행
          this.uploadimageurl.push({ url: e.target.result }); // e.target.result를 통해 이미지 url을 가져와서 uploadimageurl에 저장
          console.log({ url: e.target.result });
        };
        fileReader.readAsDataURL(getFile); //바이너리 파일을 Base64 Encode 문자열로 반환 Ex.) data:image/jpeg; base64, ….
      });
    },
    //게시글 등록
    //미리보기에서 사진 삭제돼야함 ->
    uploadImage() {
      const formData = new FormData(feedInsert); // 파일을 전송할때는 FormData 형식으로 전송
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
