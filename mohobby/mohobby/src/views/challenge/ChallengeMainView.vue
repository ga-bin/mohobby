<template>
    <v-card
      class="mx-auto"
      max-width="80%"
      outlined
    >
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
              
            />
        <!-- 파일이름, 개수 -->
        <div v-for="(list,i) in fileList"
        :key="i">
          {{list.name}}
        </div>

        <!-- 이미지 미리보기 -->
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

        <!-- 내용 -->
        <v-textarea
          name="content"
          auto-grow
          placeholder="내용을 입력해주세요!"
          value=""
          v-model="content"
        ></v-textarea>
      </v-container>

      <!-- 해시태그 -->
      <v-container fluid>
            <v-combobox
              v-model="model"
              :filter="filter"
              :hide-no-data="!search"
              :items="items"
              :search-input.sync="search"
              hide-selected
              label="Search for an option"
              multiple
              small-chips
              solo
            >
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
        <p>{{menu5}}</p>
  </v-card>
</template>

<script>

export default {
  props : {
    menu5 : String
  } ,
  data() {
    return {
    //해시태그
    activator: null,
    attach: null,
    colors: ['green', 'purple', 'indigo', 'cyan', 'teal', 'orange'],
    editing: null,
    editingIndex: -1,
    items: [
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
    x: 0,
    search: null,
    y: 0,
    //이미지Data
    uploadimageurl: [], //미리보기 이미지url
    imagecnt: 0,//업로드한 이미지개수 axious시에 넘겨줌
    fileList : [],
    file : {},
    postId : "1",
    memberId : "user1",
    formData : {},
    //sns글등록Data
    memberId : this.$store.state.id,
    content: "",
    hashtag:[],//내가 추가한 해시태그
    };
  },
  created() {
   
  },
  watch: {
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
    },
  },
  methods: {
    //해시태그
    edit (index, item) {
      if (!this.editing) {
        this.editing = item
        this.editingIndex = index
      } else {
        this.editing = null
        this.editingIndex = -1
      }
    },
    //게시글 미리보기
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
    //게시글 등록
    uploadImage() {
    //hashtag가져오기
      this.model.forEach((hashtag) => {
      console.log(hashtag.text);
      this.hashtag.push(hashtag.text);
    });
    //hashtag배열 스트링화
    const hashtags = this.hashtag.join(', ');
    console.log(hashtags);
    
      const vm = this;
      this.axios.post('/sns/myfeed/post', { // 게시글 저장
          memberId: this.memberId,
          content: this.content,
          hashtag: hashtags,
        })
        .then(function (res) {
            console.log("게시글저장 성공!"+res);
        })
        .catch(function (error) {
          console.log(error);
        })
        .finally(function (ros) {
          this.axios({
            url: "/sns/myfeed/media",	// 이미지 저장
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
          console.log("게시글등록 완료!"+ros)
        })
    },

  },
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
    font-size: .9em;
  }
</style>
