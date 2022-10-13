<!-- 댓글입력창 -->
<template>
    <div>
        <v-row>
            <v-list two-line>
               <v-subheader>댓글</v-subheader>
                <v-col cols="10" v-for="(item, i) in items" :key="i">
                  <v-list-item-avatar>
                    <img :src="require(`@/assets/image/user/${item.profileImg}`)">
                  </v-list-item-avatar>
                    <v-list-item-content>
                        <v-list-item>{{item.memberId}}</v-list-item>
                        <v-list-item-subtitle>{{item.writeDate}}</v-list-item-subtitle>
                    </v-list-item-content>
                    <v-divider></v-divider>
                </v-col>
                <v-col cols="2">
                    <!-- <v-btn class="ma-2 white--text" color="blue-grey" rounded>등록</v-btn> -->
                </v-col>
            </v-list>
        </v-row>
    </div>
</template>
<script>

export default {
    name:"CmtList",
    data() {
      return{
        items: [
                { 
                  memberId: 'Hi', 
                  writeDate: '2022/01/01 00:00',
                  profileImg: 'female.png'
                },
               ],
      }
    },
    method:{
        // this.axios{(then)

        // }

    }
}

</script>
