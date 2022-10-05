<template>
  <div class="mx-auto">
    <h3>추천 만능 재주꾼들 피드</h3>
    <v-sheet max-width="1500">
      <v-slide-group class="x-auto">
      <v-slide-item
        v-for="(item,idx) in items"
        :key="idx"
        >
          <div class="box">
<!-- 
            <v-list-item-avatar
            tile
            size="200">
              <HotLecturer></HotLecturer>
              
            </v-list-item-avatar> -->
            <HotLecturer></HotLecturer>

          </div>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </div>
</template>
<script>
import HotLecturer from "./SnsMain_hotLecturer";

export default {
    name: "HotLecturerList",
    components: {HotLecturer},
    data() {
        return {
            items: [],
        };
    },
    created() {
        this.getItems_list();
    },
    methods: {
        //hot_lecture list dummy data
        getItems_list() {
            // const item = [];
            const item_srcs = [
                { src: require(`@/assets/image/sns/logo.png`) },
                { src: "https://img.insight.co.kr/static/2020/03/06/700/w73pq7140798cym777d8.jpg" },
                { src: "https://img.insight.co.kr/static/2020/03/06/700/d852a5r04tvpa860xz92.jpg" },
                { src: "https://img.insight.co.kr/static/2020/03/06/700/o5384w751o0583qs2659.jpg" },
                { src: "https://img.insight.co.kr/static/2020/03/06/700/4o5ookw8k16p4k5jgzur.jpg" }
            ];
            for (let i = 0; i <= 20; i++) {
                this.items.push(item_srcs[Math.floor(Math.random() * item_srcs.length)]);
            }
        },
    },
}
</script>
