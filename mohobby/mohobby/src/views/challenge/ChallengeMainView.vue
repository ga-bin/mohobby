<template>
<div class="container">

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
        multiple
        @change="onImageChange"
        
      />
  </v-container>
    <!-- 이미지 미리보기 -->
    <!-- <v-container fluid> -->
    <div style="display:inline-flex; margin-left: 10px;">
      <v-img v-for="(item,i) in uploadimageurl" 
            :key="i" 
            :src="item.url"
            aspect-ratio="4/3"
            height="150px" 
            width="200px"
            lazy-src
            error 
            style="margin-right: 10px;"
      />
    </div>
    <v-container fluid>
      <div v-for="(list,i) in fileList"
            :key="i">
        {{list.name}}
      </div>
    </v-container>
    <v-btn @click="uploadImage">uploadImage</v-btn>
</div>

</template>

<script>

export default {
  data() {
    return {
    uploadimageurl: [], //이미지업로드2
    imagecnt: 0,//업로드한 이미지개수 axious시에 넘겨줌
    fileList : [],
    file : {},
    postId : "1",
    memberId : "user1",
    formData : {},
    };
  },
  created() {
   
  },
  methods: {
    onImageChange(file) {	// v-file-input 변경시
      if (!file) {
        console.log("file" + file);
        return;
      }
      const formData = new FormData();	// 파일을 전송할때는 FormData 형식으로 전송
      file.forEach((item) => {
        console.log("item.name" + item.name);//name:파일명, size:바이트(인듯),type:image/png
        formData.append('fileList', item);	// formData의 key: 'filelist', value: 이미지
        this.formData = formData;
        const reader = new FileReader();
        reader.onload = (e) => {
          console.log({url: e.target.result});
          this.uploadimageurl.push({url: e.target.result});
          // e.target.result를 통해 이미지 url을 가져와서 uploadimageurl에 저장
        };
        reader.readAsDataURL(item);
      });
    },
    //이미지 업로드
    uploadImage() {
      const vm = this;
      this.axios({
          url: "/sns/myfeed",	// 이미지 저장을 위해 back서버와 통신
          method: "POST",
          headers: {'Content-Type': 'multipart/form-data' },	// 이걸 써줘야 formdata 형식 전송가능
          data: vm.formData
        }).then(res => {
          console.log("res.data.message" + res.data.message);
          this.imagecnt = file.length;	// 이미지 개수 저장
          console.log("이미지 몇개?"+this.imagecnt)
        }).catch(err => {
          alert(err);
        });
    },
  },
};
</script>
