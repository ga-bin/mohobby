<template>
  <div class="mx-auto" style="width:800px; height:500px;">
    <v-card outlined>
      <form id="feedInsert" name="feedInsert">
        <v-container fluid>
          <input type="hidden" v-model="memberId" name="memberId" />
          <input type="hidden" v-model="hashtag" name="hashtag" />
          <!-- 파일등록부 -->
          <!-- <v-file-input
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
            accept="image/png, image/jpeg, image/jpg"
          /> -->


          <div class="main-container">
            <div class="room-deal-information-container">
                <div v-if="!files.length" class="room-file-upload-example-container">
                    <div class="room-file-upload-example">
                        <div class="room-file-notice-item">
                            사진은 한 장 이상 등록 부탁드립니다🙏
                        </div>
                        <div class="room-file-notice-item room-file-notice-item-red">
                            타인에게 불쾌감을 줄 수 있는 게시글은 비공개 처리가 될 수 있으니 유의 부탁드립니다🙏
                        </div>
                        <div class="room-file-notice-item room-file-upload-button">
                            <div class="image-box">
                                <label for="file">일반 사진 등록</label>
                                <input type="file" id="file" name="fileList" ref="files" accept="image/png, image/jpeg, image/jpg" @change="onImageChange" multiple />
                            </div>
                        </div>
                    </div>
                </div>
                <div v-else class="file-preview-content-container">
                    <div class="file-preview-container">
                        <div v-for="(file, index) in files" :key="index" class="file-preview-wrapper">
                            <div class="file-close-button" @click="fileDeleteButton" :name="file.number">
                                x
                            </div>
                            <img :src="file.preview" />
                        </div>
                        
                            <div class="image-box">
                                <label for="file">추가 사진 등록</label>
                                <input type="file" id="file" ref="files" @change="imageAddUpload" multiple />
                            </div>
                            <!-- <div class="file-close-button" @click="fileDeleteButton" :name="file.number">x</div> -->
                    </div>
                </div>
            </div>
        </div>






          <!-- 
            
              동적 FILE_INPUT_BOX TEST
          
          -->

          <!--INPUT FILE박스 추가 버튼  -->
          <!-- <div id="box"> 
              <input type="file"><input type="button" value="추가" onclick="add_filebox()">
          </div> -->



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

          <!-- 내용 -->
          <v-textarea
            name="content"
            auto-grow
            placeholder="내용을 입력해주세요!"
            value=""
            v-model="content"
          ></v-textarea>
        </v-container>
        <!-- 유저소환 -->

        <!-- 해시태그 -->
        <!-- <input type="hidden" v-model="getHashtag" :hashtag="getHashtag"  name="hashtag"> -->
        <v-container fluid>
          <v-combobox
            v-model="model"
            :hide-no-data="!search"
            :items="items"
            :search-input.sync="search"
            hide-selected
            label="Search for an option"
            multiple
            small-chips
            solo4
          >
            <!-- :filter="filter" -->
            <template v-slot:no-data>
              <v-list-item>
                <span class="subheading">Create</span>
                <v-chip :color="`${colors[nonce - 1]} lighten-3`" label small>
                  {{ search }}
                </v-chip>
              </v-list-item>
            </template>
            <template v-slot:selection="{ attrs, item, parent, selected }">
              <v-chip
                v-if="item === Object(item)"
                v-bind="attrs"
                :color="`${item.color} lighten-3`"
                :input-value="selected"
                label
                small
              >
                <span class="pr-2">
                  {{ item.text }}
                </span>
                <v-icon small @click="parent.selectItem(item)"> $delete </v-icon>
              </v-chip>
            </template>
            <template v-slot:item="{ index, item }">
              <v-text-field
                v-if="editing === item"
                v-model="editing.text"
                autofocus
                flat
                background-color="transparent"
                hide-details
                solo
                @keyup.enter="edit(index, item)"
              ></v-text-field>
              <v-chip v-else :color="`${item.color} lighten-3`" dark label small>
                {{ item.text }}
              </v-chip>
              <v-spacer></v-spacer>
              <v-list-item-action @click.stop>
                <v-btn icon @click.stop.prevent="edit(index, item)">
                  <v-icon>{{
                    editing !== item ? "mdi-pencil" : "mdi-check"
                  }}</v-icon>
                </v-btn>
              </v-list-item-action>
            </template>
          </v-combobox>
        </v-container>
      </form>
    </v-card>
    <v-btn @click="uploadImage">uploadImage</v-btn>
  </div>
</template>

<script>
export default {
data() {
  return {
  //해시태그
  activator: null,
  attach: null,
  colors: ['green', 'purple', 'indigo', 'cyan', 'teal', 'orange'],
  editing: null,
  editingIndex: -1,
  items: [ //임의로 바인딩해놓은 추천 해시태그
    { header: 'Select an option or create one' },
    {
      text: '오운완',
      color: 'blue',
    },
    {
      text: '오수완',
      color: 'red',
    },
  ],
  nonce: 1,
  menu: false,
  model: [
    {
      text: '취미',
      color: 'blue',
    },
  ],
  search: null,
  x: 0,
  y: 0,

  //이미지Data
  uploadimageurl: [], //미리보기 이미지url
  imagecnt: 0,//업로드한 이미지개수 axious시에 넘겨줌
  fileList : [],
  file : {},
  postId : "1",
  formData : {},

  //sns글등록Data
  memberId : this.$store.state.id,
  content: "",
  getHashtag:[],//내가 추가한 해시태그
  hashtag:"",//스트링화 해시태그

  
  files: [], //업로드용 파일
  filesPreview: [],
  uploadImageIndex: 0 // 이미지 업로드를 위한 변수
  };
},
created() {

},
watch: {

  //해시태그 색상변경
  model (val, prev) {
    if (val.length === prev.length) return

    this.model = val.map(v => {
      if (typeof v === 'string') {
        v = {
          text: v,
          color: this.colors[this.nonce - 1],
        }

        this.items.push(v)

        this.nonce++
      }
      return v
    })
  }
},

methods: {

    //해시태그수정
    edit (index, item) {
      if (!this.editing) {
        this.editing = item
        this.editingIndex = index
      } else {
        this.editing = null
        this.editingIndex = -1
      }
    },


/*

          
     동적 FILE_INPUT_BOX TEST
        

*/

      //이미지 미리보기
      onImageChange() {
        console.log(this.$refs.files.files);

        // this.files = [...this.files, this.$refs.files.files];
        //하나의 배열로 넣기
        let num = -1;
        for (let i = 0; i < this.$refs.files.files.length; i++) {
            this.files = [
                ...this.files,
                //이미지 업로드
                {
                    //실제 파일
                    file: this.$refs.files.files[i],
                    //이미지 프리뷰
                    preview: URL.createObjectURL(this.$refs.files.files[i]),
                    //삭제및 관리를 위한 number
                    number: i
                }
            ];
            num = i;
            //이미지 업로드용 프리뷰
            // this.filesPreview = [
            //   ...this.filesPreview,
            //   { file: URL.createObjectURL(this.$refs.files.files[i]), number: i }
            // ];
        }
        this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장
        console.log(this.files);
        // console.log(this.filesPreview);
    },

    //이미지 미리보기
    imageAddUpload() {
        console.log(this.$refs.files.files);

        // this.files = [...this.files, this.$refs.files.files];
        //하나의 배열로 넣기c
        let num = -1;
        for (let i = 0; i < this.$refs.files.files.length; i++) {
            console.log(this.uploadImageIndex);
            
            //files 에 객체로 담음
            this.files = [
                ...this.files,
                //이미지 업로드
                {
                    //실제 파일
                    file: this.$refs.files.files[i],
                    //이미지 프리뷰
                    preview: URL.createObjectURL(this.$refs.files.files[i]),
                    //삭제및 관리를 위한 number
                    number: i + this.uploadImageIndex
                }
            ];
            num = i;
        }
        this.uploadImageIndex = this.uploadImageIndex + num + 1;

        console.log(this.files); // console.log(this.filesPreview);
    },
    
    //미리보기 이미지 삭제
    fileDeleteButton(e) {
        const name = e.target.getAttribute('name');
        this.files = this.files.filter(data => data.number !== Number(name));
        // console.log(this.files);
    },


    //이미지 미리보기***********미리보기에서 사진 삭제돼야함
    // onImageChange(file) {
    //   if (!file) return;

    //   file.forEach((getFile) => {
    //     const fileReader = new FileReader();
    //     console.log("item.name: " + getFile.name);
    //     fileReader.onload = (e) => {
    //       this.uploadimageurl.push({url: e.target.result});
    //     };
    //     fileReader.readAsDataURL(getFile);
    //   });
    // },


    //게시글 등록
    uploadImage() {
      let self = this;

      //1. 받은 해시태그 배열에 담기
      this.model.forEach((hashtag) => {
        console.log("push hashtag: " + hashtag.text);
        this.getHashtag.push(hashtag.text);
      });

      //2. hashtag배열 스트링화
      const hashtags = this.getHashtag.join();
      console.log("hashtags" + hashtags);
      feedInsert.hashtag.value = hashtags;

      //폼객체 생성해서 form태그인 feedInsert 담기
      const formData = new FormData(feedInsert);   // 파일을 전송할때는 FormData 형식으로 전송
      console.log(document.getElementsByName("memberId")); //아이디 확인 완.
      console.log(formData);
      this.axios.post('/sns/myfeed', formData, { // 게시글 저장
          headers: {
            'Content-Type': 'multipart/form-data'
          },
        })
        .then(function (res) {
            console.log("게시글저장 성공!");
            console.log(this.$store.state.id);
            self.$router.push({ path: '/snsUserFeed', query: {userId : self.$store.state.id} });
        })
        .catch(function (error) {
          console.log(error);
        })
    },

  }
};
</script>
<style scoped>
.main-container {
            width: 600px;
            height: 400px;
            margin: 0 auto;
        }
        
        .room-deal-information-container {
            margin-top: 50px;
            color: #222222;
            border: 1px solid #dddddd;
        }
        
        .room-deal-information-title {
            text-align: center;
            font-size: 18px;
            line-height: 60px;
            border-bottom: 1px solid #dddddd;
        }
        
        .room-deal-information-content-wrapper {
            min-height: 50px;
            display: flex;
        }
        
        .room-deal-informtaion-content-title {
            font-size: 15px;
            display: flex;
            align-items: center;
            justify-content: center;
            width: 150px;
            background-color: #f9f9f9;
        }
        
        .room-deal-information-content {
            width: 100%;
            font-size: 14px;
        }
        
        .room-deal-option-selector {
            display: flex;
            align-items: center;
            padding: 15px;
        }
        
        .room-deal-option-item {
            width: 100px;
            height: 40px;
            display: flex;
            align-items: center;
            justify-content: center;
            border: 1px solid #cccccc;
            border-radius: 5px;
            cursor: pointer;
        }
        
        .room-deal-option-item:last-child {
            margin-left: 10px;
        }
        
        .room-deal-option-notice {
            margin-left: auto;
            font-size: 14px;
            color: #888888;
        }
        
        .room-deal-option-item-deposit {
            margin-left: 10px;
        }
        
        .room-deal-information-wrapper {
            display: flex;
            flex-direction: column;
        }
        
        .room-deal-information-option {
            padding: 10px;
            display: flex;
            align-items: center;
        }
        
        .room-deal-information-option:last-child {
            border-bottom: 1px solid #dddddd;
        }
        
        .room-deal-information-item-type {
            font-size: 13px;
            color: #fff;
            background-color: #61b6e5;
            min-width: 50px;
            height: 26px;
            display: flex;
            justify-content: center;
            align-items: center;
            border-radius: 3px;
        }
        
        .room-deal-information-item-wrapper {
            display: flex;
            align-items: center;
            margin-left: 10px;
            height: 46px;
            width: 100%;
        }
        
        .room-deal-information-item-wrapper>input {
            border: 1px solid #dddddd;
            width: 140px;
            height: 100%;
            padding: 0 15px;
            font-size: 15px;
        }
        
        .room-deal-inforamtion-won {
            margin: 0 10px;
        }
        
        .room-deal-information-example {
            color: #888888;
        }
        
        .room-deal-information-option:not(:first-child) {
            margin-top: 10px;
        }
        
        .room-deal-inforamtion-divide {
            font-size: 22px;
            margin: 0 8px;
            color: #222222;
            font-weight: 100;
        }
        
        .room-deal-close-button-wrapper {
            margin-left: auto;
            cursor: pointer;
        }
        
        .room-deal-close-button {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 22px;
            height: 22px;
            background-color: #666666;
            color: rgb(220, 220, 220);
        }
        
        .room-deal-cliked {
            background-color: rgb(235, 235, 235);
            color: rgb(170, 170, 170);
        }
        
        .room-file-upload-example {
            height: 100%;
        }
        
        .room-write-content-container {
            border-top: 1px solid #dddddd;
            min-height: 260px;
        }
        
        .room-picture-notice {
            margin: 20px;
            padding: 20px 40px;
            border: 1px solid #dddddd;
        }
        
        .file-preview-content-container {
            height: 100%;
        }
        
        .room-file-upload-wrapper {
            margin: 20px;
            border: 1px solid #dddddd;
            background-color: #f4f4f4;
            min-height: 350px;
            font-size: 15px;
            color: #888888;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100%;
        }
        
        .room-file-upload-example-container {
            display: flex;
            align-items: center;
            justify-content: center;
            /* height: 100%;
  width: 100%; */
        }
        
        .room-file-image-example-wrapper {
            text-align: center;
        }
        
        .room-file-notice-item {
            margin-top: 5px;
            text-align: center;
        }
        
        .room-file-notice-item-red {
            color: #e16e1c;
        }
        
        .image-box {
            margin-top: 30px;
            padding-bottom: 20px;
            text-align: center;
        }
        
        .image-box input[type='file'] {
            position: absolute;
            width: 0;
            height: 0;
            padding: 0;
            overflow: hidden;
            border: 0;
        }
        
        .image-box label {
            display: inline-block;
            padding: 10px 20px;
            background-color: #2ac187;
            color: #fff;
            vertical-align: middle;
            font-size: 13px;
            cursor: pointer;
            border-radius: 20px;
        }
        
        .file-preview-wrapper {
            padding: 10px;
            position: relative;
        }
        
        .file-preview-wrapper>img {
            position: relative;
            width: 200px;
            height: 180px;
            z-index: 10;
            border-radius: 10px;
        }
        
        .file-close-button {
            position: absolute;
            /* align-items: center; */
            line-height: 18px;
            z-index: 99;
            font-size: 16px;
            right: 5px;
            top: 10px;
            color: #fff;
            font-weight: bold;
            background-color: #2ac187;
            width: 20px;
            height: 20px;
            text-align: center;
            cursor: pointer;
            border-radius: 3px;
        }
        
        .file-preview-container {
            height: 100%;
            display: flex;
            flex-wrap: wrap;
        }
        
        
        .room-write-button-wrapper {
            margin-top: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            color: #222222;
        }
        
        .room-write-button-wrapper>div {
            width: 160px;
            height: 50px;
            border: 1px solid #2ac187;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 15px;
            cursor: pointer;
        }
        
        .room-write-button {
            margin-left: 15px;
            color: #fff;
            background-color: hsl(155, 74%, 52%);
        }
        
        .room-write-button:hover {
            opacity: 0.8;
        }
</style>
