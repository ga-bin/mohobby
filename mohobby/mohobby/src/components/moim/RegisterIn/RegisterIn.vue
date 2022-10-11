<template>
  <v-card
    class="mx-auto"
    max-width="1000"
    outlined
  >
    <v-list-item three-line>
      <v-list-item-avatar
        tile
        size="200"
        color="grey"
      ></v-list-item-avatar>
      <v-list-item-title class="text-h5 mb-1">
          <v-text-field
                  label="모임명"
                  required
                ></v-text-field>
        </v-list-item-title>
    </v-list-item>
<v-list-item>
  <v-list-item-content>
        <v-list-item-subtitle class="text-h5 mb-1">
          <v-container fluid>
            <v-textarea
              name="input-7-1"
              filled
              auto-grow
              placeholder="함께하고 싶은 소모임 활동을 자세히 소개해주세요. (50자 이상)"
              value=""
            ></v-textarea>
          </v-container>
        </v-list-item-subtitle>
        <hr>
  <v-list-item-content>


        <!--주제선택-->
        <v-col
      cols="12"
      sm="5">
    <v-dialog
      v-model="check"
      max-width="600px"
      >
      <template v-slot:activator="{ on, attrs }">
          <div class="buttonSelect">
            <span class="font-weight-bold">소모임 주제</span>
          </div>
        <v-btn
          color="green"
          dark
          rounded
          v-bind="attrs"
          v-on="on"
        >
          주제 선택
        </v-btn>
      </template>
      <v-sheet
        elevation="10"
        rounded="xl"
      >
          <v-card-title class="justify-center">
            <span class="font-weight-bold">지역선택</span>
          </v-card-title>
          <hr>
        <div class="pa-12">
        <!-- 취미 hobb -->
        <v-chip-group
        active-class="light-green--text"
        column
          >
          <div>
            <div class="mt-3">
          <span class="font-weight-bold">취미</span>
          </div>
          <div class="pa-1">
                <v-chip v-for="item in hobb" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!-- 스포츠/레저 sports -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">스포츠/레저</span>
            </div>
              <div class="pa-1">
                <v-chip v-for="item in sports" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!-- 어학/외국어 lang -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">어학/외국어</span>
            </div>
              <div class="pa-1">
                <v-chip v-for="item in lang" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!-- 문화/예술 arts -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">문화/예술</span>
          </div>
              <div class="pa-4">
                <v-chip v-for="item in arts" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!-- 여행/캠핑 trip -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">여행/캠핑</span>
            </div>
              <div class="pa-4">
                <v-chip v-for="item in trip" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!-- 음악 music -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">음악</span>
            </div>
              <div class="pa-4">
                <v-chip v-for="item in music" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!-- 건강/다이어트 diet -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">건강/다이어트</span>
            </div>
              <div class="pa-4">
                <v-chip v-for="item in diet" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!-- 친목/모임 amity -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">친목/모임</span>
            </div>
              <div class="pa-4">
                <v-chip v-for="item in amity" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!-- 맛집/요리 matzip <=맛집ㅋㅋㅋㅋㅋㅋㅋㅋㅋ -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">맛집/요리</span>
            </div>
              <div class="pa-4">
                <v-chip v-for="item in matzip" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!-- 인문/과학 humenities -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">인문/과학</span>
            </div>
              <div class="pa-4">
                <v-chip v-for="item in humenities" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!--  팬클럽 fan -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">팬클럽</span>
            </div>
              <div class="pa-4">
                <v-chip v-for="item in fan" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!--  게임 game -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">게임</span>
            </div>
              <div class="pa-4">
                <v-chip v-for="item in game" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!--  만화/애니메이션 ani -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">만화/애니메이션</span>
            </div>
              <div class="pa-4">
                <v-chip v-for="item in ani" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!-- 반려동물/동물 animal -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">반려동물/동물</span>
            </div>
              <div class="pa-4">
                <v-chip v-for="item in animal" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!-- 교육/공부 edu -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">교육/공부</span>
            </div>
              <div class="pa-4">
                <v-chip v-for="item in edu" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!-- IT/컴퓨터 it -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">IT/컴퓨터</span>
            </div>
              <div class="pa-4">
                <v-chip v-for="item in it" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!-- 경제/재테크 financial -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">경제/재테크</span>
            </div>
              <div class="pa-4">
                <v-chip v-for="item in financial" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!-- 종교/봉사 charity -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">종교/봉사</span>
            </div>
              <div class="pa-4">
                <v-chip v-for="item in charity" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
        <!-- 자연/귀농 nature -->
        <div>
          <div class="mt-3">
            <span class="font-weight-bold">자연/귀농</span>
            </div>
              <div class="pa-4">
                <v-chip v-for="item in nature" :key="item" class="mr-3 mb-3">
                  {{item}}
                </v-chip>
              </div>
          </div>
          </v-chip-group>
          <v-card-actions class="justify-center mr-5">
          <v-btn
          depressed
          elevation="2"
          @click="check = false"
          >
            취소
          </v-btn>
          <v-btn
          color="success"
          depressed
          elevation="2"
          @click="check = false"
          >
            저장
          </v-btn>
          </v-card-actions>
        </div>
      </v-sheet>
      </v-dialog>
      </v-col>
    </v-list-item-content>
    <hr>

          <!-- 지역선택 -->
          <v-list-item-content>
          <v-col
      cols="12"
      sm="12">
    <v-dialog
      v-model="region"
      max-width="600px"
    >
    <template v-slot:activator="{ on, attrs }">
      <div class="buttonSelect">
            <span class="font-weight-bold">소모임 활동 지역</span>
          </div>
      <v-btn
          color="green"
          dark
          rounded
          v-bind="attrs"
          v-on="on"
        >
          지역 선택
        </v-btn>
      </template>
      <v-sheet
        class="pa-4"
        elevation="10"
        rounded="xl"
      >
      <v-card-title class="justify-center">
          <span class="font-weight-bold">지역선택</span>
      </v-card-title>
      <v-card-actions>
        
      <v-text-field
            hide-details
            outlined
            label="Search"
            prepend-icon="mdi-magnify" 
          >
        </v-text-field>
        <div class="searchB">
        <v-btn
      color="success"
      dark
      large
      >
      검색
      </v-btn>
      </div>
    </v-card-actions>
    <v-card-actions >
    &nbsp;&nbsp;
    <v-icon color="green">mdi-crosshairs-gps</v-icon>
    &nbsp;&nbsp;
    <div class="font-weight-bold">내 위치</div>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <div>대구시 달서구</div>
   </v-card-actions>
   <br>
   <br>
   <div class="font-weight-bold">검색 결과</div>
   <v-card-actions class="justify-center">
    <v-btn
    @click="region=false"
    >
      닫기
    </v-btn>
   </v-card-actions>
      </v-sheet>
      </v-dialog>
      </v-col>
      </v-list-item-content>

      <hr>
      <!-- 최대인원 -->
  <v-list-item-content>
    <div class="flexcon">
      <v-col
      cols="5"
      sm="5">
      <div>
      <span>최대 인원 설정</span>
      </div>
                <v-select
                  :items="['30명', '50명', '100명']"
                  label="최대 인원"
                  required
                ></v-select>
        </v-col>
        </div>
    </v-list-item-content>
    <small class="text-lg-left">양식을 다 채워서 가입해주세요!</small>

    <v-card-actions class="lastbutton">
      <v-spacer></v-spacer>
      <v-btn
        outlined
        rounded
        text
      >
        취소하기
      </v-btn>
      <v-btn
        outlined
        rounded
        text
      >
        생성하기
      </v-btn>
    </v-card-actions>
      </v-list-item-content>
</v-list-item>
  </v-card>
</template>
<script>
  export default {
    data() {
      return {
        items : [{
          src : "https://cdn.class101.net/images/84d01f2e-ec28-4e30-8ed1-fc6e55954e42/960xauto.webp"
        }],
        hobb: [
          '독서',
          '글쓰기',
          'DIY/공예',
          '가죽/목공',
          '드론/액션캠/RC',
          '바둑/장기',
          '취미',
          ],
        sports: [
          '러닝/걷기',
          '자전거',
          '테니스',
          '등산/산악',
          '야구',
          '축구/풋살',
          '당구',
          '서핑',
          '자동차',
        ],
        lang:[
          '영어',
          '일본어',
          '중국어',
          '외국어',
          '스페인어/포르투갈어'
        ],
        arts: [
          '그림/일러스트',
          '디자인',
          '캘리그라피/서예',
          '사진',
          '댄스/무용',
          '공연/뮤지컬',
          '영화',
          '미술/전시',
          '문화/예술'
        ],
        trip: [
          '국내여행',
          '해외여행',
          '캠핑/백패킹'
        ],
        music: [
          '노래',
          '악기',
          '음악'
        ],
        diet: [
          '피트니스',
          '요가/필라테스'
        ],
        amity: [
          '친목/모임',
          '육아/맘 모임'
        ],
        matzip: [
          '맛집',
          '요리/레시피'
        ],
        humenities: [
          '역사',
          '심리학',
          '인문/철학'
        ],
        fan: [
          '프로야구',
          '팬클럽'
        ],
        game: [
          '게임'
        ],
        ani: [
          '만화/애니메이션'
        ],
        animal: [
          '반려동물/동물'
        ],
        edu: [
          '공부'
        ],
        it: [
          'IT'
        ],
        financial: [
          '경제/재테크'
        ],
        charity: [
          '봉사/자선'
        ],
        nature: [
          '꽃/식물'
        ],
        check : false,
        region : false
      }
    }
  }
  </script>
  
<style scoped>
.searchB {
    margin-left: 10px;
}
.buttonSelect {
  margin-bottom: 30px;
}
.flexcon {
  display:flex;
}
</style>