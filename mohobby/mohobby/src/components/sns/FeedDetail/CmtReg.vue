<!-- 댓글입력창 -->
<template>
    <div>
        <v-row>
            <v-col cols="10">
                <v-text-field
                label=""
                placeholder="댓글을 입력해주세요"
                solo
                rounded
                v-model="inputCmt"
                ></v-text-field>
            </v-col>
            <v-col cols="2">
                <v-btn class="ma-2 white--text" color="blue-grey" rounded>등록</v-btn>
           </v-col>
        </v-row>
    </div>
</template>
<script>

export default {
    name:"CmtReg",
    data() {
      return{
        inputCmt:"",
      }
    },
    method:{

    }
}

</script>