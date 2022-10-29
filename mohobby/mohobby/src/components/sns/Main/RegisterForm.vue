<template>
  <div class="mx-auto" style="width:1050px; height:500px;">
    <v-card outlined>
      <div class="room-file-upload-example">

        <div class="room-file-notice-item">
            사진은 한 장 이상 등록 부탁드립니다🙏
        </div>

        <div class="room-file-notice-item room-file-notice-item-red">
            타인에게 불쾌감을 줄 수 있는 게시글은 비공개 처리가 될 수 있으니 유의 부탁드립니다🙏
        </div>
      </div>

      <form id="feedInsert" name="feedInsert" v-on:submit.prevent>
        <v-container fluid>
          <input type="hidden" v-model="memberId" name="memberId" />
          <input type="hidden" v-model="hashtag" name="hashtag" />



          <div class="main-container">

            <div class="room-deal-information-container">
                <!-- 추가버튼 -->
                <div 
                  class="plus_btn"
                  rounded
                  @click="addFileList(i)"
                >
                  <v-icon color="#2ac187" size="30">mdi-plus</v-icon>
                  <v-icon color="#2ac187" size="30">mdi-camera</v-icon>
                  </div>
                <div class="room-file-upload-example-container">

                


                <div v-for="(file,i) in fileList" :key="i" style="width:200px; height:200px;  display: inline-block;">
                  <v-file-input
                      v-model="file.file"
                      @change="onImageChange(i)"
                      style="margin-top:20px;"
                      label="이곳을 클릭해주세요🙏"
                      type="file"
                      class="mx-auto"
                      id="file"
                      name="fileList"
                      prepend-icon="mdi-camera"
                      dense
                      accept="image/png, image/jpeg, image/jpg"
                    />

                    <!-- 이미지 미리보기 -->
                    <div class="img_box" style="display: inline-flex;">
                      <v-img                    
                        :src="file.url"
                        aspect-ratio="4/3"
                        height="150"
                        width="150"
                        lazy-src
                        error
                        style="margin-top: 10px; margin-right: 10px"
                      />
                    </div>



                    <!-- 삭제버튼 -->
                    <div
                        class="del_btn"
                        rounded
                        @click="delFileList(i)"
                      >
                        -
                    </div>

                </div> 

              </div>
              
              </div>
            
            </div>


          <!-- 내용 -->
          <v-textarea
            name="content"
            auto-grow
            placeholder="내용을 입력해주세요!"
            value=""
            counter="2000"
            v-model="content"
            @change="checkContent(content)"
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
            label="해시태그를 등록해보세요!"
            multiple
            small-chips
            solo
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
    <v-btn @click="uploadImage" rounded color="#2ac187" class="white--text">가보자고</v-btn>
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
  items: [ // Top 6해시태그 추천 키워드
    { header: '최근 인기있는 해시태그를 추가해보세요!' },
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
  fileList: [ {
    file: "",
    url:"",
  }],
  file : {},
  postId : "1",
  formData : {},

  //sns글등록Data
  memberId : this.$store.state.id,
  content: "",
  getHashtag:[],//내가 추가한 해시태그
  hashtag:"",//해시태그 스트링화
  };
},
created() {
  this.getHotHashtags();
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

/*

          
     동적 FILE_INPUT_BOX
        

*/


//상단바 - top6해시태그 리스트
getHotHashtags() {
  
  this.axios('/sns/main/hashtag')
  .then(res => {
    console.log(res.data);

    for(let i = 0; i<res.data.length; i++){
      let hashtag = res.data[i].hashtag;
      this.items.push({
        text: hashtag,
        color: 'pink',
      })
    }
    console.log(this.items);
  }).catch(err =>{
    console.log(err);
  });


},


 // 유효성검사 & 미리보기
onImageChange(i) {
  let file = this.fileList[i].file;
  if (!file)  return; //file없으면 return
  console.log("file------>");
  console.log(file);

  //파일 형식 검사
  if(!file.type.match("image/.*")){
    this.$swal("png, jpg, jpeg파일만 부탁드립니다🙏");
  };


      const fileReader = new FileReader();
      console.log("file.name: " + file.name);
      
      fileReader.onload = (e) => {
        //이미지 url push
        this.fileList[i].url = e.target.result
      };
      fileReader.readAsDataURL(file);
},



addFileList(i) {
  // let file = this.fileList[i].name;
  // if (!file || file == "" ) {
  //   this.$swal("사진이 첨부되지 않은 추가칸이 있는지 확인 부탁드립니다🙏");
  //   return; //file없으면 return
  // }
  if(this.fileList.length > 4){
    this.$swal("사진은 5장까지만 추가 부탁드립니다🙏")
    return; //추가한것부터 1개로 취급해서 계산됨. 즉, 파일 5개 이상 추가 못하도록
  }
      //파일 초기화
      this.fileList.push({
        file: '',
        url: '',
      })
    },

delFileList(i) {
  if(this.fileList.length == 1) { //fileList가 1개면 초기화하고 
    this.fileList[0].file = ''; 
    this.fileList[0].url = '';
  } else {
    this.fileList.splice(i, 1); //아니면 인덱스번호 잘라내기
  }
},


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

    //게시글 등록



    //사진 업로드
    uploadImage() {
      
      let self = this;
      console.log(self.fileList);

      if(self.content.length > 2000) {
        self.$swal("내용은 2000자 이내로 부탁드립니다🙏");
        return;
      }

      // if(self.fileList.length[0] = ""){
      //     self.$swal("이미지는 최소 한 장 이상 첨부 부탁드립니다🙏");
      //     return;
      // }

      // console.log("파일길이"+self.fileList.length);
      // let file = self.fileList.file;
      // if (!file) { self.$swal("추가한 이미지는 등록 혹은 삭제 부탁드립니다🙏");
      //   return; //file없으면 return
      // }

      this.model.forEach((hashtag) => {
        console.log("push hashtag: " + hashtag.text);
        this.getHashtag.push(hashtag.text);
      });


      //hashtag배열 스트링화
      const hashtags = this.getHashtag.join();
      console.log("hashtags" + hashtags);
      feedInsert.hashtag.value = hashtags;

      const formData = new FormData(feedInsert);   // 파일을 전송할때는 FormData 형식으로 전송
      console.log(feedInsert);
      console.log(document.getElementsByName("memberId")); //아이디 확인 완.

      this.axios.post('/sns/myfeed', formData, { // 게시글 저장
          headers: {
            'Content-Type': 'multipart/form-data'
          },
        })
        .then(function (res) {
            console.log("게시글저장 성공!");
            // console.log(this.$store.state.id);
            self.$router.push({ path: '/snsUserFeed', query: {userId : self.memberId} });
        })
        .catch(function (error) {
          console.log(error);
        })
    },

  }
};
</script>
<style scoped>
#att_zone {
  width: 660px;
  min-height: 150px;
  padding: 10px;
  border: 1px dotted #00f;
}

#att_zone:empty:before {
  content: attr(data-placeholder);
  color: #999;
  font-size: 0.9em;
}
.imagePreviewWrapper2 {
  width: 250px;
  height: 250px;
  display: block;
  cursor: pointer;
  margin: 0 auto 30px;
  background-size: cover;
  background-position: center center;
}
.plus_btn{
  margin-left:20px;
  border-radius: 20px;
  padding:10px;
  color: green;
  font-weight: bold;
  font-size: x-large;
  display: inline-block; 
  margin-right:10px;
  cursor:pointer;
}
.del_btn{
  color:red;
  font-weight: bold;
  border-radius: 20px;
  padding:10px;
  font-size: xx-large;
  display: inline-block;
  cursor:pointer;
}

.img_box{
  margin-left: 40px;
}

/* ///////////// */
.main-container {
            min-width: 800px;
            
            margin: 0 auto;
        }
        
        .room-deal-information-container {
            height: 400px;
            margin-top: 50px;
            color: #222222;
            /* border: 1px solid #dddddd; */
            border-radius: 8px;
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
