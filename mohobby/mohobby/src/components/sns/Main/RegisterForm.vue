<template>
  <div class="mx-auto" style="width:1050px; height:500px;">
    <v-card outlined>
      <form id="feedInsert" name="feedInsert" v-on:submit.prevent>
        <v-container fluid>
          <input type="hidden" v-model="memberId" name="memberId" />
          <input type="hidden" v-model="hashtag" name="hashtag" />
        <div v-for="(file,i) in fileList" :key="i" style="width:200px; height:200px; display: inline-block;">
          <!-- <input
                class="input"
                type="file"
                style="width: 200px"
                @change="onImageChange"
                name="fileList"
              /> -->
              
              <v-file-input
                class="mx-auto"
                label="이미지 등록 부탁드립니다🙏"
                type="file"
                style="width:200px; display: inline-block;"
                v-model="file.file"
                prepend-icon="mdi-camera"
                counter
                show-size
                dense
                @change="onImageChange"
                name="fileList"
                accept="image/png, image/jpeg, image/jpg"
              />          
              
              <img src="" height="200" alt="Image preview...">



          <!-- 파일이름, 개수 -->
          <!-- <div v-for="(list, i) in fileList.file" :key="i">
            {{ list.name }}
          </div> -->

          <!-- 이미지 미리보기 -->
          
          <!-- <div style="display: inline-flex; margin-left: 10px">
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
          </div> -->

              <v-btn 
                style="display: inline-block;"
                color="green"
                class="plus_btn"
                rounded
                @click="addFileList(i)"
              >
                +
              </v-btn>
              <v-btn
                color="red"
                class="del_btn"
                rounded
                @click="delFileList(i)"
              >
                -
              </v-btn>
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
  fileList: [ {
    file: "",
  }],
  file : {},
  postId : "1",
  formData : {},
  //sns글등록Data
  memberId : this.$store.state.id,
  content: "",
  getHashtag:[],//내가 추가한 해시태그
  hashtag:"",//스트링화 해시태그
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

/*

          
     동적 FILE_INPUT_BOX TEST
        

*/

// onImageChange(i) {
//   const preview = document.querySelector('img');
//   const file = document.querySelector('input[type=file]').files[i];
//   const reader = new FileReader();

//   reader.addEventListener("load", function () {
//     // convert image file to base64 string
//     preview.src = reader.result;
//     console.log(reader.result);
//   }, false);

//   if (file) {
//     reader.readAsDataURL(file);
//   }
// },




onImageChange(file) {
  if (!file) return;
  console.log(file);
      const fileReader = new FileReader();
      console.log("item.name: " + file.name);
      fileReader.onload = (e) => {
        this.uploadimageurl.push({url: e.target.result});
      };
      fileReader.readAsDataURL(file);
},



addFileList(i) {
  if(this.fileList.length > 4){
    return;
  }
      this.fileList.push({
        file: '',
      })
    },

delFileList(i) {
  if(this.fileList.length == 1) {
    this.fileList[0].file = '';
  } else {
    this.fileList.splice(i, 1);
  }
},




/////////////////////////////////////////////////////////////////////


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

    //첫번째 사진을 썸네일로
    uploadImage() {
      let self = this;
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
  color:white;
  font-weight: bold;
}
.del_btn{
  color:white;
  font-weight: bold;
}
</style>
