<template>
  <div class="container">
    <v-card-text>
      <v-container>
        <v-row>
          <v-col cols="12" sm="6" md="4">
            <div @click="follow()">
              <v-card class="mx-auto pa-16" width="344" height="220" color="green accent-3">
                <div class="thumbnail-wrappper mx-auto">
                  <v-img src="@/assets/image/moim/pngwing.com.png"></v-img>
                </div>
              </v-card>
            </div>
            <div class="text">
              <v-file-input label="File input" outlined dense prepend-icon="mdi-camera"></v-file-input>
            </div>
          </v-col>
          <v-col cols="12" sm="6" md="8">
            <span>소개글</span><br><br>
            <v-textarea name="input-7-1" no-resize filled height="245"
              placeholder="함께하고 싶은 소모임 활동을 자세히 소개해주세요. (50자 이상)" value=""></v-textarea>
          </v-col>
        </v-row>
        <v-row class="mt-12">
          <v-col col="12" sm="3" md="6">
            <div class="title">
              <v-icon color="green">mdi-map-marker</v-icon> 주로 어느지역에서 활동 하나요?
            </div><br><br>
            <somoimregion></somoimregion>
          </v-col>
          <v-divider vertical></v-divider>
          <v-col col="12" sm="3" md="6">
            <div class="title">
              <v-icon color="green">mdi-wifi</v-icon> 주로 어떤 주제로 활동 하나요?
            </div><br><br>
            <somoimtopic></somoimtopic>
          </v-col>
        </v-row>
        <v-row class="mt-16">
          <v-col col="12" sm="12" md="12">
            <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn class="mr-3">취소</v-btn>
            <v-btn color="success">저장</v-btn>
          </v-card-actions>
          </v-col>
        </v-row>
      </v-container>
    </v-card-text>
  </div>
</template>
<script>
import somoimtopic from "@/components/moim/setting/somoimtopic.vue"
import somoimregion from "@/components/moim/setting/somoimregion.vue"
export default {
  data() {
    return {
      dialog: false
    };
  },
  components: { somoimtopic, somoimregion }
}

</script>
<style scoped>
.container {
  width: 90%;
}

.title {
  font-weight: bold;
  font-size: 1.25rem !important
}

.text {
  text-align: center;
  margin-top: 30px;
  font-size: 20px;
  font-weight: bold;
}

.thumbnail-wrappper {
  width: 75px;
}


v-img {
  max-width: 100%;
  height: auto;
}
</style>