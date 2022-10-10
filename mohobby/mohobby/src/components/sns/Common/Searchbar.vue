<template>
    <div id="searchbar">
        <v-container>
            <!-- 글 등록창 이동 -->
            <v-card-actions>
                <v-spacer></v-spacer>
                <div id="used-main-dropbox">
                    <v-select @change="dropVal()" v-model="select" :items="items" item-text="name" item-value="value" label="정렬"
                        color="#212529" persistent-hint return-object single-line dense width="50">
                    </v-select>
                </div>
                <v-btn  icon 
                        class="d-flex flex-column-reverse pa-3 secondary rounded-circle d-inline-block white--text"
                        color="#2ac187"
                        small
                        @click="goRegForm()">
                    <v-icon small>mdi-plus-thick</v-icon>
                </v-btn>
            </v-card-actions>
            <v-row dense
                   style="height:50px">
                <v-col cols="6" 
                       class="mx-auto"
                >
                    <!-- 검색창 -->
                    <v-autocomplete 
                        v-model="ctg" 
                        :items="ctg" 
                        item-text="tag" 
                        item-value="tag" 
                        label="해시태그나 유저 아이디를 검색해보세요"
                        dense 
                        outlined
                        :search-input.sync="userInput" 
                        @input="userInput=null"
                        menu-props="{'closeOnContentClick': true}"
                        class="rounded-xl"
                        append-icon="mdi-magnify"
                        @change="search()"
                        >
                        <!-- @change="changeCoatings" -->
                    </v-autocomplete>
                </v-col>
            </v-row>

            <!-- 해시태그 검색 -->
            <div id="chip">
                <v-row justify="space-around">
                    <v-col cols="8">
                        <v-sheet >
                            <v-chip-group active-class="primary--text">
                                <v-chip v-for="hotTag in hotTags" 
                                        :key="hotTag"
                                         color="#2ac187"
                                         class="mx-auto white--text font-weight-bold"
                                         >
                                    #{{ hotTag }}
                                </v-chip>
                            </v-chip-group>
                        </v-sheet>
                    </v-col>
                </v-row>
            </div>
        </v-container>
    </div>
</template>
<script>
// import skills from "./skills.js";
export default {
    name: "snsSearchbar",
    data() {
        return {
            hotTags: [
                "오운완",
                "다이어트",
                "헬린이",
                "오수완",
                "주짓수",
                "삼대오백"
            ],
            ctg: [
                { tag: '운동' },
                { tag: '오공완' },
                { tag: '다이어트' },
                { tag: '건강' },
                { tag: '공예' },
                { tag: '연극' },
                { tag: '취향' },
                { tag: '메이크업' },
                { tag: '오운완' },

            ],
            userInput: null,
            memberId: 123,
            items: [{
                name: '최신순',
                value: '최신순'
                },
                {
                name: '인기순',
                value: '인기순'
                },
            ],
            data: [],
            word: ""
        }
    },
    watch: {
        userInput(val) {
            if (!val) {
                return
            }
            this.fetchEntriesDebounced()
        },
    },
    methods: {
        fetchEntriesDebounced() {
            // cancel pending call
            clearTimeout(this._timerId)

            // delay new call 500ms
            this._timerId = setTimeout(() => {
                // maybe : this.fetch_data()
                this.people = this.itemData ? this.itemData : []
            }, 500)
        },
        goRegForm() {
            this.$router.push({ name: "snsFeedRegister" });
        },
        search(){
            //유저 아이디 or 닉네임 조회
            let searchValue = document.querySelector("#rounded-xl").value;

            this.axios({
            url : '/sns/search/user',        
            params : {
                memberId : searchValue,
                nickname : searchValue,
            }
            }).then(res => {
            console.log(res);
            this.data = res.data;
            console.log(this.data);

            }).catch(err =>{
            console.log(err);
            });
        },
        enterkey: function (e) {
            if (window.event.keyCode == 13) {
            this.search(e);
        }
      },
      checkbox: function () {
        const ckbox = document.querySelector(".form-check-input");
        const is_cked = ckbox.checked;
        var isChecked = document.querySelector(".form-check-input").innerText = is_cked
        console.log(isChecked);
        // axios({
        //   url: "http://localhost:8088/zippy/used/main",
        //   methods: "GET",
        //   params: {
        //     keyword: "",
        //     location: "",
        //     category: "",
        //     checked: isChecked
        //   }
        // }).then(res => {
        //   console.log(res);
        //   this.data = res.data;
        // }).catch(err => {
        //   console.log(err)
        // })
      },
      dropVal: function () {
        let dropValue = this.select.value;
        console.log(dropValue);
        axios({
          url : "http://localhost:8088/zippy/used/main",
          methods : "GET",
          params : {
            keyword: "",
            location: "",
            category: "",
            checked: "",
            dropbox : dropValue
          }
        })
       }
      }
    }


</script>
<style scoped>
#searchbar{
    width:80%;
    margin: 0 auto;
}

label {
    margin-right: 3px;
}
</style>