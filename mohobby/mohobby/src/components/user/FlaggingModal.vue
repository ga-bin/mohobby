<template>
<div>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      scrollable
      max-width="300px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="primary"
          dark
          v-bind="attrs"
        >
          신고하기
        </v-btn>
      </template>
      <v-card>
        <v-card-title>Select Country</v-card-title>
        <v-divider></v-divider>
        <v-card-text style="height: 300px;">
          <v-radio-group
            v-model="dialogm1"
            column
          >
            <v-radio
              label="부적절한 게시물 개시."
              value="mo1"
            ></v-radio>
            <v-radio
              label="홍보성 초대 반복."
              value="mo2"
            ></v-radio>
            <v-radio
              label="청소년에게 음란성 초대글 유포."
              value="mo3"
            ></v-radio>
            <v-radio
              label="다른 소모임에 대한 욕설, 비방"
              value="mo4"
            ></v-radio>
             <v-radio
              label="사이트 목적과 맞지 않은 소모임"
              value="mo5"
            ></v-radio>
            <v-radio
              label="기타"
              value="mo6"
            ></v-radio>
          </v-radio-group>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-btn
            color="blue darken-1"
            text
            @click="dialog = false"
          >
            Close
          </v-btn>
          <v-btn
            color="blue darken-1"
            text
            @click="dialog = false"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
  <v-row justify="center">
    <v-dialog
      v-model="dialog"
      scrollable
      max-width="300px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="primary"
          dark
          v-bind="attrs"
          v-on="on"
        >
          신고하기
        </v-btn>
      </template>
      <v-card>
        <v-card-title>Select Country</v-card-title>
        <v-divider></v-divider>
        <v-card-text style="height: 300px;">
          <v-radio-group
            v-model="dialogm1"
            column
          >
            <v-radio
              label="부적절한 게시물 개시."
              value="mo1"
            ></v-radio>
            <v-radio
              label="다른 유저에게 욕설, 비방"
              value="mo2"
            ></v-radio>
            <v-radio
              label="게시글, 댓글 도배"
              value="mo3"
            ></v-radio>
            <v-radio
              label="홍보성 게시물 반복적 개시"
              value="mo4"
            ></v-radio>
            <v-radio
              label="기타"
              value="mo5"
            ></v-radio>
          </v-radio-group>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-btn
            color="blue darken-1"
            text
            @click="dialog = false"
          >
            Close
          </v-btn>
          <v-btn
            color="blue darken-1"
            text
            @click="dialog = false"
          >
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
  </div>
</template>
<script>
export default {
    name: '',
    components: {},
    data() {
        return {
        dialogm1: '',
        dialog: false,
    }
},
    beforeCreate() {},
    created() {},
    beforeMount() {},
    mounted() {},
    beforeUpdate() {},
    updated() {},
    beforeUnmount() {},
    unmounted() {},
    methods: {
        moimFlagging() {

        },
        userFlagging() {

        },
        invite() {

        }
    }
}
</script>