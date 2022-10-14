<template>
  <div id="container">
    <v-expansion-panels v-model="panel" multiple>
      <v-card-title class="text-5">
        가입 조건 설정
      </v-card-title>
      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4">
              성별
            </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <div class="memberCount" v-if="open" key="0">
                </div>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-row justify="space-around" no-gutters>
            <v-spacer />
            <v-col cols="3">
              <v-select
                :items="gender"
                label="선택"
                dense
              ></v-select>
            </v-col>
          </v-row>
        </v-expansion-panel-content>
        </v-expansion-panel>

      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4">
              나이
            </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <span v-if="open"></span>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-row justify="space-around" no-gutters>
            <v-col cols="3">
              <v-select
                :items="items"
                label="최소나이"
                dense
              ></v-select>
            </v-col>

            <v-col cols="3">
              <v-select
                :items="items"
                label="최대나이"
                dense
              ></v-select>
            </v-col>
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>

      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4">
              최대 멤버 수
            </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <div class="memberCount" v-if="open" key="0">
                </div>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-row justify="space-around" no-gutters>
            <v-spacer />
            <v-col cols="3">
              <v-select
                :items="count"
                label="선택"
                dense
              ></v-select>
            </v-col>
          </v-row>
        </v-expansion-panel-content>
        
      </v-expansion-panel>
    </v-expansion-panels>
    
    <v-card-actions class="mr-5 mb-5 mt-5">
    <v-spacer />
    <v-btn>저장</v-btn>
    </v-card-actions>
 
  </div>
</template>
<script>
export default {
  data: () => ({
    panel: [0, 1, 2],
    radioGroup: '',
    count: ['10명', '30명', '50명', '100명'],
    items: [],
    trip: {
        member: null,
        start: null,
        end: null,
      },
    gender: ['남성','여성','모두']
    }),
    methods: {
      selectAge: function () {
        for(let i=2022; i>=1923; i--){
            this.items.push(i);
        }
      },
    },
    created() {
      this.selectAge()
    }
  
}
</script>
<style scoped>
#container {
  max-width: 60%;
  margin: 2% auto;
}
</style>