<template>
  <v-navigation-drawer app>
    <div class="text-center mt-4 pa-10">
      <v-avatar class="mb-4" color="grey darken-1" size="64">
        <v-card @click="console.log('test')">
          <v-img
            aspect-ratio="30"
            src="data:image/jpeg;base64,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"
          />
        </v-card>
      </v-avatar>
      <h4 class="white--text">IU</h4>
    </div>

    <v-divider></v-divider>

    <v-list>
      <v-list-group
        v-for="link in links"
        :key="link.index"
        :prepend-icon="link.icon"
        no-action
      >
        <template v-slot:activator>
          <v-list-item-content>
            <v-list-item-title v-text="link.text"></v-list-item-title>
          </v-list-item-content>
        </template>

        <v-list shaped subheader>
          <v-list-item-group color="primary">
           <template v-for="subheader in link.subheaders">
            <v-subheader :key="subheader.text" v-if="subheader.text != ''">{{ subheader.text }}</v-subheader>
            <v-list-item 
              v-for="child in subheader.items"
              :key="child.id"
              @click="$router.push({ path: child.route+child.catg }).catch(()=>{$router.go(0)})"
              link
            >
              <v-list-item-icon>
                <v-icon></v-icon>
              </v-list-item-icon>
              <v-list-item-content>
                <v-list-item-title v-text="child.text"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </template>
          </v-list-item-group>
        </v-list>
      </v-list-group>
    </v-list>
  </v-navigation-drawer>
</template>
<script>
export default {
  data() {
    return {
      active_list: 0,
      links: [
        { icon: "mdi-format-list-bulleted", text: "클래스 둘러보기", subheaders: [
          { text: "", items: [
            { id: 0, text: "전체", route: "/class/list/", catg: 'all' }, 
            { id: 1, text: "취미", route: "/class/list/", catg: 1000 }, 
            { id: 2, text: "스포츠/레저", route: "/class/list/", catg: 1001 }, 
            { id: 3, text: "어학/외국어", route: "/class/list/", catg: 1002 }, 
            { id: 4, text: "문화/예술", route: "/class/list/", catg: 1003 }, 
            { id: 5, text: "여행/캠핑", route: "/class/list/", catg: 1004 }, 
            { id: 6, text: "음악", route: "/class/list/", catg: 1005 }, 
            { id: 7, text: "건강/다이어트", route: "/class/list/", catg: 1006 }, 
            { id: 8, text: "친목/모임", route: "/class/list/", catg: 1007 },
            { id: 9, text: "맛집/요리", route: "/class/list/", catg: 1008 }, 
            { id: 10, text: "인문/과학", route: "/class/list/", catg: 1009 }, 
            { id: 11, text: "팬클럽", route: "/class/list/", catg: 1010 }, 
            { id: 12, text: "게임", route: "/class/list/", catg: 1011 }, 
            { id: 13, text: "만화/애니메이션", route: "/class/list/", catg: 1012 }, 
            { id: 14, text: "반려동물/동물", route: "/class/list/", catg: 1013 }, 
            { id: 15, text: "교육/공부", route: "/class/list/", catg: 1014 },
            { id: 16, text: "IT/컴퓨터", route: "/class/list/", catg: 1015 }, 
            { id: 17, text: "경제/재테크", route: "/class/list/", catg: 1016 }, 
            { id: 18, text: "종교/봉사", route: "/class/list/", catg: 1017 },
            { id: 19, text: "자연/귀농", route: "/class/list/", catg: 1018 },
          ]}
        ] },
        { icon: "mdi-account", text: "내 클래스", subheaders: [
          { text: "학습 관리", items: [
            { id: 20, text: "내 학습", route: "", catg: "" },
            { id: 21, text: "출석관리", route: "", catg: "" },
            { id: 22, text: "강의노트", route: "", catg: "" },
            { id: 23, text: "작성한 글 목록", route: "", catg: "" },
          ] },
          { text: "수강신청 관리", items: [
            { id: 24, text: "찜바구니", route: "", catg: "" },
            { id: 25, text: "주문내역", route: "", catg: "" },
          ] },
        ] },
        { icon: "mdi-crown", text: "만능재주꾼", subheaders: [
          { text: "클래스 개설", items: [
            { id: 26, text: "개설신청", route: "", catg: "" },
            { id: 27, text: "심사확인", route: "", catg: "" },
          ] },
          { text: "클래스 관리", items: [
            { id: 28, text: "클래스 수정", route: "", catg: "" },
            { id: 29, text: "질문과 답변", route: "", catg: "" },
            { id: 30, text: "출석체크", route: "", catg: "" },
            { id: 31, text: "수익 관리", route: "", catg: "" },
          ] }
        ]},
        { icon: "mdi-ab-testing", text: "테스트", subheaders: [
          { text:"", items: [
            { id: 32, text: "오픈뱅킹테스트", route: "/class/test/OpenBankingTest", catg: "/" }, 
            { id: 33, text: "에디터테스트", route: "/class/test/QuillEditorTest", catg: "/" }, 
            { id: 34, text: "iamport테스트", route: "/class/test/iamportTest", catg: "/" }, 
            { id: 35, text: "계좌 실명 조회 테스트", route: "/class/test/AccountRealNameTest", catg: "/" }, 
            { id: 36, text: "QR코드 생성 테스트", route: "/class/test/AttdQRTest", catg: "/" }, 
            { id: 37, text: "카카오 맵 테스트", route: "/class/test/KakaoMapTest", catg: "/" }, 
          ] },
        ]}
      ],
    };
  },
  watch: {
  },
  methods: {
    clickList(idx) {
      this.active_list = idx;
    }
  }
};
</script>
<style scoped>
.text-center {
  background-color: #2ac187;
}
</style>
