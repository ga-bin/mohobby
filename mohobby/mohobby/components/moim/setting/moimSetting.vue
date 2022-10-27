<template>
  <div class="container">
    <v-card-text>
      <v-container>
        <v-row>
          <v-col cols="12" sm="6" md="4">
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
          </v-col>
          <v-col cols="12" sm="6" md="8">
            <span>소개글</span><br><br>
            <v-textarea name="input-7-1" no-resize filled height="245"
              placeholder="함께하고 싶은 소모임 활동을 자세히 소개해주세요. (50자 이상)" v-model="intro"></v-textarea>
          </v-col>
        </v-row>
        <v-row class="mt-12">
          <v-col col="12" sm="3" md="6">
            <div class="title">
              <v-icon color="green">mdi-map-marker</v-icon> 주로 어느지역에서 활동 하나요?
            </div><br><br>
            <somoimregion></somoimregion>
          </v-col>
          <v-divider vertical></v-divider>
          <v-col col="12" sm="3" md="6">
            <div class="title">
              <v-icon color="green">mdi-wifi</v-icon> 주로 어떤 주제로 활동 하나요?
            </div><br><br>
            <somoimtopic></somoimtopic>
          </v-col>
        </v-row>
        <v-row class="mt-16">
          <v-col col="12" sm="12" md="12">
            <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn class="mr-3">취소</v-btn>
            <v-btn color="success"  @click="uploadImage">저장</v-btn>
          </v-card-actions>
          </v-col>
        </v-row>
      </v-container>
    </v-card-text>
  </div>
</template>
<script>
import somoimtopic from "@/components/moim/setting/somoimtopic.vue"
import somoimregion from "@/components/moim/setting/somoimregion.vue"
export default {
  name: "",
  data() {
    return {
      dialog: false,
      intro: '',
      sampleData: "",
      uploadimageurl: "", //이미지업로드2
      fileList: [],
      file: {},
      formData: {},
    };
  },
  components: { somoimtopic, somoimregion },
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
  }
}

</script>
<style scoped>
.container {
  width: 90%;
}

.title {
  font-weight: bold;
  font-size: 1.25rem !important
}

.text {
  text-align: center;
  margin-top: 30px;
  font-size: 20px;
  font-weight: bold;
}

.thumbnail-wrappper {
  width: 75px;
}


v-img {
  max-width: 100%;
  height: auto;
}
</style>