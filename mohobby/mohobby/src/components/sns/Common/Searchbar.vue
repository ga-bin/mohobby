<template>
    <div id="searchbar">
        <div class="regFeed">
            <!-- 글쓰기: 비회원-로그인폼 -->
            <v-card-actions  v-if="role===0" >
                <v-spacer></v-spacer>
                <div class="text-center">
                    <v-dialog
                            v-model="noneuser"
                            width="500"
                    >
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn text>
                            <v-chip
                            color="success"
                            outlined
                            v-bind="attrs"
                            v-on="on"
                            >
                            <v-icon left>mdi-plus</v-icon>글쓰기
                            </v-chip>
                        </v-btn>
                    </template>
                        <v-card>
                            <br><br>
                            <v-card-text class="font-weight-bold center">
                                로그인이 필요합니다 !
                            </v-card-text>
                            <v-divider></v-divider>
                            <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn
                                color="success"
                                depressed
                                @click="join"
                                >로그인하러 가기</v-btn>
                                <v-btn
                                depressed
                                @click="noneuser=false"
                                >닫기</v-btn>
                            </v-card-actions>
                        </v-card>
                    </v-dialog>
                </div>
            </v-card-actions>
            <!-- 글쓰기: 회원-글쓰기폼 -->
            <v-card-actions v-else>
                <v-spacer></v-spacer>
                <v-btn text>
                    <v-chip
                    color="success"
                    outlined
                    @click="select"
                    >
                    <v-icon left>mdi-plus</v-icon>
                    글쓰기
                    </v-chip>
                </v-btn>
            </v-card-actions>
        </div>
        <!-- 검색창 -->
        <div>
        <v-card-actions>
            <v-spacer></v-spacer>
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
                        style="height:50px"
                />
        </v-card-actions>
        <!-- 해시태그 검색 -->
        <v-card-actions>
            <v-spacer></v-spacer>
            <div id="chip">
                <v-row justify="space-around">
                    <v-col cols="12">
                        <v-sheet ref="getHashtag" @click="search($event)">
                            <v-chip-group active-class="primary--text">
                                <v-chip v-for="hashtag in hashtags" 
                                        :key="hashtag"
                                         color="#2ac187"
                                         class="mx-auto white--text font-weight-bold"
                                         >
                                    {{ hashtag.hashtag }}
                                </v-chip>
                            </v-chip-group>
                        </v-sheet>
                    </v-col>
                </v-row>
            </div>
        </v-card-actions>
    </div>
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
            //검색
            data: [],
            word: "",
            //moim
            role : 0,
            noneuser : false,
            hashtags: [],

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
    created() {
        this.getHotHashtags();
    },
    methods: {
        //top6해시태그
        getHotHashtags() {
            this.axios('/sns/main/hashtag').then(res => {
            console.log(res);
            this.hashtags = res.data;
            console.log(this.hashtags);
          }).catch(err =>{
            console.log(err);
          });
        },
        //해시태그 가져오기
        search(e){
            //유저 아이디 or 닉네임 조회
            let getHashtag = e.target.innerText;
            console.log(getHashtag);
            this.axios('/sns/search/hashtag', {
                params : {
                    hashtag : getHashtag
                }
            }).then(res => {
                console.log(res);
                this.data = res.data;
                console.log(this.data);

            }).catch(err =>{
                console.log(err);
            });
        },
        //dialog
        select : function() {
            if (this.role !== 0 ) {
            this.$router.push({ path: 'snsFeedRegister' })
            }
        },
        join() {
            this.$router.push({ path: 'login' })
        },
        //
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

        //
        // search(){
        //     //유저 아이디 or 닉네임 조회
        //     let searchValue = document.querySelector("#rounded-xl").value;
        //     console.log(searchValue)
        //     this.axios({
        //     url : '/sns/search/user',        
        //     params : {
        //         memberId : searchValue,
        //         nickname : searchValue,
        //     }
        //     }).then(res => {
        //     console.log(res);
        //     this.data = res.data;
        //     console.log(this.data);

        //     }).catch(err =>{
        //     console.log(err);
        //     });
        // },
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