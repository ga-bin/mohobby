<template>
  <v-card
  class="mx-auto"
  max-width="80%"
  outlined
  >
  <form id = "feedInsert" name="feedInsert">
    <v-container fluid>
      <input type="hidden" :memberId="memberId" v-model="memberId" name="memberId">
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
  </form>
      <v-btn @click="uploadImage">uploadImage</v-btn>
</v-card>
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
      text: 'Foo',
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
  //미리보기에서 사진 삭제돼야함 ->
  //첫번째 사진을 썸네일로
  uploadImage() {
    let self = this;
    const formData = new FormData(feedInsert);	// 파일을 전송할때는 FormData 형식으로 전송
    formData.append('fileList',feedInsert);
    console.log(feedInsert);

    console.log(document.getElementsByName("memberId")); //아이디 확인 완.
    this.axios.post('/sns/myfeed', formData, { // 게시글 저장
        headers: {
          'Content-Type': 'multipart/form-data'
        },
      })
      .then(function (res) {
          console.log("게시글저장 성공!"+res);
          self.$router.push({ name: 'snsUserFeed', query: {memId : self.$store.state.id} });
      })
      .catch(function (error) {
        console.log(error);
      })
  },

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
