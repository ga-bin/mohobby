<template>
    <div id="searchbar">
        <v-container>
            <v-row dense
                   style="height:100px">
                <v-col cols="6" 
                       class="mx-auto"
                >
                    <!-- <v-btn icon right>
                        <v-icon>mdi-magnify</v-icon>
                    </v-btn> -->
                        <!-- 글 등록창 이동 -->
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn icon 
                                   class="d-flex flex-column-reverse pa-3 secondary rounded-circle d-inline-block white--text"
                                   color="#2ac187"
                                   @click="insertFeed">
                                <v-icon>mdi-plus-thick</v-icon>
                            </v-btn>
                        </v-card-actions>
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
                        >
                        <!-- @change="changeCoatings" -->
                    </v-autocomplete>
                </v-col>
            </v-row>

            <!-- 핫한 해시태그 5개 -->
            <div id="chip">
                <v-row justify="space-around">
                    <v-col cols="6">
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
            memberId: 123
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
        insertFeed() {
            this.$router.push({ path : 'snsFeedInsert' })
            // this.$router.push({ name : 'snsFeedInsert', params: { memberId : this.memberId }})
        }
    },

}

</script>
<style scoped>
body {
    margin-bottom: 24px;
    margin: 0 auto;
}

label {
    margin-right: 3px;
}
</style>