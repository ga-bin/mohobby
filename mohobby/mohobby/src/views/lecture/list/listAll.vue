<template>
<v-container>
    <v-row>
    <v-stepper 
        non-linear
        rounded
        flat
        width="209"
        
    >
      <v-stepper-header>
        <v-stepper-step
          editable
          step="All"
          class="stepAll"
          color="green"
        >
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step
          editable
          step="ON"
          color="green"
        >
        </v-stepper-step>

        <v-divider></v-divider>

        <v-stepper-step
          step="OFF"
          editable
          color="green"
        >
        </v-stepper-step>
      </v-stepper-header>
    </v-stepper>
    <v-btn 
        icon 
        v-for="select in selectList"
        :key="select.title"
    >
      {{ select.title }}
    </v-btn>
    <v-col
        class="d-flex"
        cols="5"
        sm="2"
    >
    <v-select
        :items="items"
        label="Outlined style"
        dense
        outlined
    ></v-select>
    </v-col>
    </v-row>
    <v-row>
        <v-badge
            overlap
            left
            offset-x="90"
            offset-y="45"
            v-for="item in itemList"
            :key="item.title"
            :content="item.type"
            :color="item.type === 'ONLINE' ? 'green' : 'red'"
            style="padding: 15px"
        >
            <div>
            <v-img
                lazy-src="https://picsum.photos/id/11/10/6"
                height="250"
                width="365"
                :src="item.img"
            >
                
                <v-btn
                    :color="item.heart ? 'pink' : 'gray'"
                    icon
                    small
                    absolute
                    right
                    @click = "item.heart = !item.heart"
                >
                    <v-icon>{{ item.heart ? 'mdi-heart' : 'mdi-heart-outline' }}</v-icon>
                </v-btn>
            </v-img>
            <v-card-text 
                class="my-2"
                style="font-size: 1.1em; padding: 5px 2px; word-break: keep-all; width: 360px "
            >
                {{ item.title }}
                <div class="my-2 text-subtitle-1">
                    {{ item.catg + ' | ' + item.name }}
                </div>
            </v-card-text>
            </div>
        </v-badge>
    </v-row>
</v-container>
</template>

<script>
export default {
    data() {
        return {
            heart: true,
            selectList: [
                {
                    title: 'All',
                    click: true,
                },
            ],
            itemList: [
                {
                    title: '초보 그림러가 인기 작가가 되는 법, 콕스의 독학비법서',
                    type: 'ONLINE',
                    heart: true,
                    catg: '디지털 드로잉',
                    name: '콕스',
                    img: 'https://cdn.class101.net/images/e16f5209-bf9a-49c4-b429-bfa8792bfb51/1920xauto.webp',
                },
                {
                    title: '샐러드만으로 맛과 양 모두 즐기는 샐러드 레시피 & 드레싱 클래스',
                    type: 'OFFLINE',
                    heart: false,
                    catg: '요리·음료',
                    name: '리화쌤',
                    img: 'https://cdn.class101.net/images/d404c753-6885-4e7b-abda-4a500b5cb38e/1920xauto.webp',
                },
                {
                    title: `국내1위 여성 운동유튜버 홈트계의 끝판왕 '힙으뜸'`,
                    type: 'OFFLINE',
                    heart: true,
                    catg: '운동',
                    name: '심으뜸',
                    img: 'https://cdn.class101.net/images/4dd174ea-6e77-41dd-a087-2b7f42c26aae/1920xauto.webp',
                },
                {
                    title: `귀여운 낙서로 돈 버는 법, 캐릭터로 이모티콘 연금 만들기!`,
                    type: 'ONLINE',
                    heart: false,
                    catg: '드로잉',
                    name: '김하나',
                    img: 'https://cdn.class101.net/images/eca46b1e-7fdd-45da-a3a6-8d9b5dfb342a/1920xauto.webp',
                },
                {
                    title: '초보 그림러가 인기 작가가 되는 법, 콕스의 독학비법서',
                    type: 'ONLINE',
                    heart: true,
                    catg: '디지털 드로잉',
                    name: '콕스',
                    img: 'https://cdn.class101.net/images/e16f5209-bf9a-49c4-b429-bfa8792bfb51/1920xauto.webp',
                },
                {
                    title: `국내1위 여성 운동유튜버 홈트계의 끝판왕 '힙으뜸'`,
                    type: 'OFFLINE',
                    heart: true,
                    catg: '운동',
                    name: '심으뜸',
                    img: 'https://cdn.class101.net/images/4dd174ea-6e77-41dd-a087-2b7f42c26aae/1920xauto.webp',
                },
                {
                    title: '샐러드만으로 맛과 양 모두 즐기는 샐러드 레시피 & 드레싱 클래스',
                    type: 'OFFLINE',
                    heart: false,
                    catg: '요리·음료',
                    name: '리화쌤',
                    img: 'https://cdn.class101.net/images/d404c753-6885-4e7b-abda-4a500b5cb38e/1920xauto.webp',
                },
                {
                    title: `귀여운 낙서로 돈 버는 법, 캐릭터로 이모티콘 연금 만들기!`,
                    type: 'ONLINE',
                    heart: false,
                    catg: '드로잉',
                    name: '김하나',
                    img: 'https://cdn.class101.net/images/eca46b1e-7fdd-45da-a3a6-8d9b5dfb342a/1920xauto.webp',
                },
            ]
        }
    },
    mounted() {
        console.log(document.querySelector('.stepAll'));
        document.querySelector('.stepAll').click();
    }

}
</script>

<style scoped>
    .v-stepper__step__step { 
        width: 35px; 
        height: 35px; 
        font-size: 0.8em;
    }  
    .v-stepper__step--editable:hover {
        background-color: transparent !important;
    }
    
</style>