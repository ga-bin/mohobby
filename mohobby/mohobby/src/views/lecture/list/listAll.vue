<template>
    <v-container fluid>
        <div>
            <v-card
                class="d-flex justify-end"
                flat
                tile
            >
                <v-card
                    flat
                    tile
                    style="padding-top: 20px; margin-right: 10px;"
                >
                    <v-btn
                        class="mx-1 selectBtn"
                        fab
                        dark
                        v-for="select in selectList"
                        :key="select.title"
                        :color="select.color"
                        depressed
                        small
                        @click="selectType"
                        :ripple="false"
                    >
                        {{ select.title }}
                    </v-btn>
                </v-card>
                <v-card
                    class="pa-2"
                    tile
                    flat
                >
                    <v-select 
                        :items="listStd" 
                        item-text="title"
                        item-value="value"
                        :menu-props="{ bottom: true, offsetY: true }"
                        attach
                        style="width: 160px"
                        v-model="defaultSelect"
                    />
                </v-card>
            </v-card>
        </div>
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
                        @click="$router.push('/')"
                    >
                
                        <v-btn
                            :color="item.heart ? 'pink' : 'gray'"
                            icon
                            small
                            absolute
                            right
                            @click = "clickHeart"
                        >
                            <v-icon>{{ item.heart ? 'mdi-heart' : 'mdi-heart-outline' }}</v-icon>
                        </v-btn>
                    </v-img>
                    <v-card-text 
                        class="my-2"
                        style="font-size: 1.1em; padding: 5px 2px; word-break: keep-all; width: 360px "
                    >
                        <span @click="$router.push('/')" class="item-title">{{ item.title }}</span>
                        <div class="my-2 text-subtitle-1">
                            <span class="item-catg">{{ item.catg }}</span> {{ ' | ' }} <span class="item-name">{{ item.name }}</span>
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
            defaultSelect: {
                value: "1"
            },
            listStd: [
                { title: "인기순", value: "1" },
                { title: "최신순", value: "2" },
            ],
            heart: true,
            selectList: [
                {
                    title: 'All',
                    click: true,
                    color: 'green',
                },
                {
                    title: 'ON',
                    click: false,
                    color: '#9f9f9f',
                },
                {
                    title: 'OFF',
                    click: false,
                    color: '#9f9f9f',
                },
            ],
            collection: [
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
                    title: '초보 그림러가 인기 작가가 되는 법, 콕스의 독학비법서2',
                    type: 'ONLINE',
                    heart: true,
                    catg: '디지털 드로잉',
                    name: '콕스',
                    img: 'https://cdn.class101.net/images/e16f5209-bf9a-49c4-b429-bfa8792bfb51/1920xauto.webp',
                },
                {
                    title: `국내1위 여성 운동유튜버 홈트계의 끝판왕 '힙으뜸'2`,
                    type: 'OFFLINE',
                    heart: true,
                    catg: '운동',
                    name: '심으뜸',
                    img: 'https://cdn.class101.net/images/4dd174ea-6e77-41dd-a087-2b7f42c26aae/1920xauto.webp',
                },
                {
                    title: '샐러드만으로 맛과 양 모두 즐기는 샐러드 레시피 & 드레싱 클래스2',
                    type: 'OFFLINE',
                    heart: false,
                    catg: '요리·음료',
                    name: '리화쌤',
                    img: 'https://cdn.class101.net/images/d404c753-6885-4e7b-abda-4a500b5cb38e/1920xauto.webp',
                },
                {
                    title: `귀여운 낙서로 돈 버는 법, 캐릭터로 이모티콘 연금 만들기!2`,
                    type: 'ONLINE',
                    heart: false,
                    catg: '드로잉',
                    name: '김하나',
                    img: 'https://cdn.class101.net/images/eca46b1e-7fdd-45da-a3a6-8d9b5dfb342a/1920xauto.webp',
                },
            ],
            itemList: [],
        }
    },
    created() {
        this.itemList = this.collection;
    },
    methods: {
        selectType: function() {
            let current = event.currentTarget;
            let btnList = document.querySelectorAll('.selectBtn');

            let result;

            for(let i = 0 ; i < btnList.length; i++) {
                if(btnList[i] === current) {
                    this.selectList[i].color='green';
                    this.selectList[i].click=true;

                    result = i;
                } else {
                    this.selectList[i].color='#9f9f9f';
                    this.selectList[i].click=false;
                }
            }

            if(result==0) {
                this.itemList = this.collection;
            } else if(result==1) {
                this.itemList = [];
                for(let item of this.collection) {
                    if(item.type == 'ONLINE') {
                        this.itemList.push(item);
                    }
                }
            } else if(result==2) {
                this.itemList = [];
                for(let item of this.collection) {
                    if(item.type == 'OFFLINE') {
                        this.itemList.push(item);
                    }
                }
            }

        },
        clickHeart: function() {
            event.stopPropagation();

            let current = event.currentTarget.parentElement.parentElement.parentElement.parentElement;
            let currentList = document.querySelectorAll('.v-badge.v-badge--left.v-badge--overlap.theme--light');

            let itemNum;
            let collectionNum;

            for(let i = 0; i < currentList.length; i++) {
                if(currentList[i] === current) {
                    itemNum = i;
                };
            };
            
            for(let i = 0; i < this.collection.length; i++) {
                if(this.collection[i] === this.itemList[itemNum]) {
                    collectionNum = i;
                }
            };

            this.itemList[itemNum].heart = !this.collection[collectionNum].heart;
            this.collection[collectionNum].heart = this.itemList[itemNum].heart;
        },
        test: function() {
            console.log('test');
        }
    }

}
</script>

<style scoped>
    .v-stepper__step--editable:hover {
        background-color: transparent !important;
    }


    ::v-deep .v-stepper__step__step { 
        width: 35px; 
        height: 35px; 
    }
    
    .item-title:hover, .item-catg:hover, .item-name:hover, .v-image:hover {
        cursor: pointer;
    }
    
</style>