
<!-- 글쓰기폼 -->
<template>
  <v-card
    class="mx-auto"
    max-width="80%"
    outlined
>
<!-- 파일 업로드 -->
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
        accept="image/*"
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

    <!-- 내용 -->
    <v-container fluid>
      <v-textarea
        name="content"
        auto-grow
        placeholder="내용을 입력해주세요!"
        value=""
        v-model="this.content"
      ></v-textarea>
    </v-container>
    {{this.content}}
    <!-- 해시태그 -->
    <v-container fluid>
            <v-combobox
              v-model="model"
              :hide-no-data="!search"
              :items="items"
              :search-input.sync="search"
              hide-selected
              label="해시태그를 입력해보세요!"
              multiple
              small-chips
              solo
            >
            <!-- :filter="filter" -->
          <template v-slot:no-data>
            <v-list-item>
              <span class="subheading">Create</span>
              <v-chip
                :color="`${colors[nonce - 1]} lighten-3`"
                label
                small
              >
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
              <v-icon
                small
                @click="parent.selectItem(item)"
              >
                $delete
              </v-icon>
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
            <v-chip
              v-else
              :color="`${item.color} lighten-3`"
              dark
              label
              small
            >
              {{ item.text }}
            </v-chip>
            <v-spacer></v-spacer>
            <v-list-item-action @click.stop>
              <v-btn
                icon
                @click.stop.prevent="edit(index, item)"
              >
                <v-icon>{{ editing !== item ? 'mdi-pencil' : 'mdi-check' }}</v-icon>
              </v-btn>
            </v-list-item-action>
          </template>
        </v-combobox>
      </v-container>
      <v-btn @click="uploadImage">uploadImage</v-btn>
  </v-card>
</template>
<script>
//  import Tagify from '@yaireo/tagify'
 export default {
  name:'RegisterForm',
  data: () => ({
    activator: null,
    attach: null,
    colors: ['green', 'purple', 'indigo', 'cyan', 'teal', 'orange'],
    editing: null,
    editingIndex: -1,
    items: [ //해시태그 추천란
      { header: '추천 해시태그를 선택해보세요!' },
      {
        text: '오운완', //해시태그란에 표시되는 텍스트
        color: 'blue', //해시태그 색상
      },
      {
        text: '주짓수',
        color: 'red',
      },
    ],
    nonce: 1,
    menu: false,
    model: [],
    x: 0,
    search: null,
    y: 0,
    //file
    uploadimageurl: [], //이미지업로드
    imagecnt: 0,//업로드한 이미지개수 axious시에 넘겨줌
    fileList : [], //이미지미리보기 담을 파일list
    file : {}, //
    memberId : "",
    formData : {},
    
    snsPostVO: {
      memberId : this.memberId,
      content: "",
    }
  }),
  created(){
    this.memberId = this.$store.state.id;
  },
  watch: {
    //해시태그
    model (val, prev) {
      if (val.length === prev.length) return
    
      this.model = val.map(v => {
        if (typeof v === 'string') {
          v = {
            text: v,
            color: this.colors[this.nonce - 1],
          }

          this.items.push(v)
          console.logt(this.items);

          this.nonce++
        }
        return v
      })
    },
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

     //이미지2
     //이미지 조건 처리
     fileChange: function (e) {
      const file = e.target.files;
      let validation = true;
      let message = '';

      if (file.length > 5) {
          validation= false;
          message = `파일은 5개 까지만 등록 가능합니다.`
      }

      if (file[0].size > 1024 * 1024 * 2) {
          message = `${message}, 파일은 용량은 2MB 이하만 가능합니다.`;
          validation = false;
      }

      if (file[0].type.indexOf('image') < 0) {
          message = `${message}, 이미지 파일만 업로드 가능합니다.`;
          validation = false;
      }

      if (validation) {
          this.file = file
      }else {
          this.file = '';
          alert(message);
      }
    },
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

      
      this.axios({
          url: "/sns/myfeed/meida",	// 이미지 저장을 위해 back서버와 통신
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded; charset=utf-8"},	// 이걸 써줘야 formdata 형식 전송가능
          data: this.snsPostVO,ㅔ
        }).then(res => {
          console.log("글 등록 완료!");
        }).catch(err => {
          alert(err);
        });
      //이미지 전송 
      const vm = this;
      this.axios({
          url: "/sns/myfeed/meida/",	// 이미지 저장을 위해 back서버와 통신
          method: "POST",
          headers: {'Content-Type': 'multipart/form-data' },	// 이걸 써줘야 formdata 형식 전송가능
          data: vm.formData,
        }).then(res => {
          console.log("res.data.message" + res.data.message);
          this.imagecnt = file.length;	// 이미지 개수 저장
          console.log("이미지 몇개?"+this.imagecnt)
        }).catch(err => {
          alert(err);
        });
      }
    },
  }

  /*
  <!-- 파일 업로드 -->  
<v-container fluid>
    <input multiple="multiple" 
            type="file" 
            id="fileId" 
            ref="fileInput"
            @change="inputData()" >


    //이미지처리
    inputData() {
      //파일 추가시마다 fileList배열에 파일 푸시
      this.fileList.push(this.$refs.fileInput.files[0]);
    },
    saveData() {
      //form데이터 객체 생성
      const formData = new FormData();
      //fileList에 담긴 파일들의 길이만큼 for문 -> formData 객체에 추가(append)
      this.fileList.forEach(function(file){
        formData.append('files', file);
      });
      this.axios({
          url: "/sns/myfeed",	// 이미지 저장을 위해 back서버와 통신
          method: "POST",
          headers: {'Content-Type': 'multipart/form-data'},	// 이걸 써줘야 formdata 형식 전송가능
          data: formData,
        }).then(res => {
          console.log(res.data.message);
        }).catch(err => {
          console.log(err);
        });
    },


          
  */ 
 </script>
 <style scoped>
  .imagePreviewWrapper2 {
    width: 250px;
    height: 250px;
    display: block;
    cursor: pointer;
    margin: 0 auto 30px;
    background-size: cover;
    background-position: center center;
 }

</style>