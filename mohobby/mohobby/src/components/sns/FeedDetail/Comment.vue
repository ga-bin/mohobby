<template>
    <div>
        <v-row>
            <v-col cols="10">
                <v-text-field
                label=""
                placeholder="댓글을 입력해주세요"
                solo
                rounded
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
    name:"Comment",
    data() {
      return{
        card_list : [],
        noneUser : 9 //memberId=="" && !memberId
      }
    },
    method:{

    }
}

</script>
