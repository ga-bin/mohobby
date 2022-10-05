<template>
    <div class="m-auto">
        <v-container>
            <v-row dense>
                <v-col cols="4">
                    <v-btn icon right>
                            <v-icon>mdi-magnify</v-icon>
                    </v-btn>
                        <!-- 글 등록창 이동 -->
                    <v-btn icon right>
                        <router-link to="/snsFeedInsert"><v-icon>mdi-plus-thick</v-icon></router-link>
                    </v-btn>
                    <v-autocomplete 
                        v-model="ctg" 
                        :items="ctg" 
                        item-text="tag" 
                        item-value="tag" 
                        label="해시태그나 유저를 검색해보세요"
                        dense 
                        outlined
                        :search-input.sync="userInput" 
                        @input="userInput=null"
                        menu-props="{'closeOnContentClick': true}" 
                        >
                        <!-- @change="changeCoatings" -->
                    </v-autocomplete>
                </v-col>
            </v-row>

            <!-- 핫한 해시태그 5개 -->
            <div>
                <v-row justify="space-around">
                    <v-col cols="5" sm="100" md="30">
                        <v-sheet class="py-4 px-1">
                            <v-chip-group active-class="primary--text">
                                <v-chip v-for="hotTag in hotTags" 
                                        :key="hotTag">
                                    {{ hotTag }}
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
                "태그1",
                "태그2",
                "태그3",
                "태그4",
                "태그5",
                "태그6"
            ],
            ctg: [
                { tag: '운동' },
                { tag: '취미' },
                { tag: '다이어트' },
                { tag: '건강' },
                { tag: '공예' },
                { tag: '운운' },
                { tag: '취향' },
                { tag: '다람쥐' },
                { tag: '운빨' },

            ],
            userInput: null,
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