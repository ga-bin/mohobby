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
      persistent
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
        <v-sheet
          class="pa-3 primary text-right"
          dark
          rounded="t-xl"
        >
          <v-btn
            class="ml-2"
            icon
            @click="check = false"
          >
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-sheet>

        <div class="pa-4">
        <!-- 취미 -->
        <v-chip-group
            active-class="primary--text"
            column
          >
            <div>
              <div>
              <v-title>
                취미
              </v-title>
              </div>
            <v-chip
              v-for="hobby in hobb"
              :key="hobby"
            >
              {{ hobby }}
            </v-chip>
            </div>
            

          <!-- 스포츠/레저 -->
            <div>
              <div>
              <v-title>
                  스포츠/레저
              </v-title>
              </div>
            <v-chip
              v-for="sport in sports"
              :key="sport"
            >
              {{ sport }}
            </v-chip>
            </div>
            <br>
            <!-- 어학/외국어 -->
            <div>
              <div>
              <v-title>
                  어학/외국어
              </v-title>
              </div>
            <v-chip
              v-for="language in lang"
              :key="language"
            >
              {{ language }}
            </v-chip>
            </div>
            <br>

            <!-- 문화/예술 -->
            <div>
              <div>
              <v-title>
                문화/예술
              </v-title>
              </div>
            <v-chip
              v-for="culArt in arts"
              :key="culArt"
            >
              {{ culArt }}
            </v-chip>
            </div>
            <br>

            <!-- 여행/캠핑 -->
            <div>
              <div>
              <v-title>
                여행/캠핑
              </v-title>
              </div>
            <v-chip
              v-for="travel in trip"
              :key="travel"
            >
              {{ travel }}
            </v-chip>
            </div>
            <br>

            <!-- 음악 -->
            <div>
              <div>
              <v-title>
                음악
              </v-title>
              </div>
            <v-chip
              v-for="musics in music"
              :key="musics"
            >
              {{ musics }}
            </v-chip>
            </div>
            <br>

            <!-- 건강/다이어트 -->
            <div>
              <div>
              <v-title>
                건강/다이어트
              </v-title>
              </div>
            <v-chip
              v-for="diets in diet"
              :key="diets"
            >
              {{ diets }}
            </v-chip>
            </div>
            <br>

            <!-- 친목/모임 -->
            <div>
              <div>
              <v-title>
                친목/모임
              </v-title>
              </div>
            <v-chip
              v-for="friendship in amity"
              :key="friendship"
            >
              {{ friendship }}
            </v-chip>
            </div>
            <br>

            <!-- 맛집/요리 -->
            <div>
              <div>
              <v-title>
                맛집/요리
              </v-title>
              </div>
            <v-chip
              v-for="cook in matzip"
              :key="cook"
            >
              {{ cook }}
            </v-chip>
            </div>
            <br>

            <!-- 인문/과학 -->
            <div>
              <div>
              <v-title>
                인문/과학
              </v-title>
              </div>
            <v-chip
              v-for="humen in humenities"
              :key="humen"
            >
              {{ humen }}
            </v-chip>
            </div>
            <br>

            <!--  팬클럽 -->
            <div>
              <div>
              <v-title>
                팬클럽
              </v-title>
              </div>
            <v-chip
              v-for="fanclub in fan"
              :key="fanclub"
            >
              {{ fanclub }}
            </v-chip>
            </div>
            <br>

            <!--  게임 -->
            <div>
              <div>
              <v-title>
                게임
              </v-title>
              </div>
            <v-chip
              v-for="games in game"
              :key="games"
            >
              {{ games }}
            </v-chip>
            </div>
            <br>

            <!--  만화/애니메이션 -->
            <div>
              <div>
              <v-title>
                만화/애니메이션
              </v-title>
              </div>
            <v-chip
              v-for="animation in ani"
              :key="animation"
            >
              {{ animation }}
            </v-chip>
            </div>
            <br>


             <!-- 반려동물/동물 -->
             <div>
              <div>
              <v-title>
                반려동물/동물
              </v-title>
              </div>
            <v-chip
              v-for="pet in animal"
              :key="pet"
            >
              {{ pet }}
            </v-chip>
            </div>
            <br>

            <!-- 교육/공부 -->
            <div>
              <div>
              <v-title>
                교육/공부
              </v-title>
              </div>
            <v-chip
              v-for="education in edu"
              :key="education"
            >
              {{ education }}
            </v-chip>
            </div>
            <br>

            <!-- IT/컴퓨터 -->
            <div>
              <div>
              <v-title>
                IT/컴퓨터
              </v-title>
              </div>
            <v-chip
              v-for="computer in it"
              :key="computer"
            >
              {{ computer }}
            </v-chip>
            </div>
            <br>

            <!-- 경제/재테크 -->
            <div>
              <div>
              <v-title>
                경제/재테크
              </v-title>
              </div>
            <v-chip
              v-for="computer in financial"
              :key="computer"
            >
              {{ computer }}
            </v-chip>
            </div>
            <br>

            <!-- 종교/봉사 -->
            <div>
              <div>
              <v-title>
                종교/봉사
              </v-title>
              </div>
            <v-chip
              v-for="religion in charity"
              :key="religion"
            >
              {{ religion }}
            </v-chip>
            </div>
            <br>

            <!-- 자연/귀농 -->
            <div>
              <div>
              <v-title>
                자연/귀농
              </v-title>
              </div>
            <v-chip
              v-for="farming in nature"
              :key="farming"
            >
              {{ farming }}
            </v-chip>
            </div>
            <br>
          </v-chip-group>

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
      persistent
      max-width="600px"
    >
    <template v-slot:activator="{ on, attrs }">
      <div class="buttonSelect">
            <span class="h3">소모임 활동 지역</span>
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
          <span class="text-h5">지역선택</span>
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
    <v-vinder></v-vinder>
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