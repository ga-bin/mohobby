<template>
  <div>
    <!-- 이미지 등록하기 -->
    <v-container fluid>
      <v-file-input
        class="mx-auto"
        label="이미지 파일을 등록해주세요!(jpg,png,jpeg 형식만 가능)"
        type="file"
        filled
        prepend-icon="mdi-camera"
        counter
        show-size
        dense
        accept="image/*"
        @change="onImageChange"
      />
    </v-container>
    <!-- 이미지 미리보기 -->
    <!-- <v-container fluid> -->
    <div style="display: inline-flex; margin-left: 10px">
      <v-img
        :src="uploadimageurl"
        aspect-ratio="4/3"
        height="150px"
        width="200px"
        lazy-src
        error
        style="margin-right: 10px"
      />
    </div>
    <v-container fluid>
      <div v-for="(list, i) in fileList" :key="i">
        {{ list.name }}
      </div>
    </v-container>
    <v-btn @click="uploadImage">uploadImage</v-btn>
  </div>
</template>
<script>
export default {
  name: "",
  components: {},
  data() {
    return {
      sampleData: "",
      uploadimageurl: "", //이미지업로드2
      fileList: [],
      file: {},
      formData: {},
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    // 이미지 미리보기
    onImageChange(file) {
      // v-file-input 변경시
      if (!file) {
        console.log("file" + file);
        return;
      }
      const formData = new FormData(); // 파일을 전송할때는 FormData 형식으로 전송
      //console.log("file.name" + file.name); //name:파일명, size:바이트(인듯),type:image/png
      formData.append("file", file); // formData의 key: 'filelist', value: 이미지
      this.formData = formData;
      // 미리보기
      const reader = new FileReader();
      reader.onload = (e) => {
        this.uploadimageurl = e.target.result;
        // e.target.result를 통해 이미지 url을 가져와서 uploadimageurl에 저장
      };
      reader.readAsDataURL(file);
    },
    //이미지 업로드
    uploadImage() {
      const vm = this;
      this.axios({
        url: "/memberProfileUpdate", // 이미지 저장을 위해 back서버와 통신
        method: "POST",
        headers: { "Content-Type": "multipart/form-data" }, // 이걸 써줘야 formdata 형식 전송가능
        data: vm.formData,
      })
        .then((res) => {
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
