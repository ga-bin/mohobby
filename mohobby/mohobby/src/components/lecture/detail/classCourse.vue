<template>
<v-container>
  <v-expansion-panels
      v-model="panel"
      multiple
      flat
      
    >
      <v-expansion-panel
        v-for="(item,i) in items"
        :key="i"
      >
        <v-expansion-panel-header>
          <v-row justify="start" align="center">
            <v-chip label color="#2b2b2b" dark>{{ i+1 }}</v-chip>
            <div style="padding-left: 10px"><h3>{{ item.header }}</h3></div>
          </v-row>
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <v-list
            dense
            nav
          >
            <v-list-item
              v-for="(child,j) in item.content"
              :key="j"
              link
            >
              <div style="font-weight: bold; color:#AAABB7; padding-right: 30px;">{{ j+1 | idx }}</div>
              <div v-if="child.preview == 1" style="margin-right:20px; padding:3px 7px; background-color: #dfeff1; color:#188ba7; border-radius: 5px; font-size: 0.9em;">{{ '미리보기' }}</div>
              <v-list-item-content>
                <h3 style="font-size: 1.1em;">{{ child.title }}</h3>
              </v-list-item-content>
              <div style="font-weight: bold; color:#AAABB7;">{{ child.runtime | runtime }}</div>
            </v-list-item>
          </v-list>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
</v-container>
</template>

<script>
export default {
  created() {
    for(let i = 0; i < this.items.length; i++) {
      this.panel.push(i);
    }
  },
  data() {
    return {
      panel: [],
      items: [
        {
          header: '프로그래밍 혁명',
          content: [
            {
              title: '코딩배우기',
              runtime: '142',
              preview: 1,
            },
            {
              title: '얏호',
              runtime: '1680',
              preview: 1,
            },
            {
              title: '코딩이 뭘까?',
              runtime: '239',
              preview: 1,
            },
            {
              title: '프로그래밍 혁명에 참여하는 방법',
              runtime: '237',
              preview: 1,
            },
          ],
        },
        {
          header: '입문자가 알기 힘든 두 가지',
          content: [
            {
              title: '코딩배우기',
              runtime: '142',
              preview: 1,
            },
            {
              title: '얏호',
              runtime: '1680',
              preview: 0,
            },
            {
              title: '코딩이 뭘까?',
              runtime: '239',
              preview: 0,
            },
            {
              title: '프로그래밍 혁명에 참여하는 방법',
              runtime: '237',
              preview: 0,
            },
          ],
        },
        {
          header: '프로그래밍 분야들',
          content: [
            {
              title: '코딩배우기',
              runtime: '142',
              preview: 0,
            },
            {
              title: '얏호',
              runtime: '1680',
              preview: 0,
            },
            {
              title: '코딩이 뭘까?',
              runtime: '239',
              preview: 0,
            },
            {
              title: '프로그래밍 혁명에 참여하는 방법',
              runtime: '237',
              preview: 0,
            },
          ],
        },
        {
          header: '공부하기!',
          content: [
            {
              title: '코딩배우기',
              runtime: '142',
              preview: 0,
            },
            {
              title: '얏호',
              runtime: '1680',
              preview: 0,
            },
            {
              title: '코딩이 뭘까?',
              runtime: '239',
              preview: 0,
            },
            {
              title: '프로그래밍 혁명에 참여하는 방법',
              runtime: '237',
              preview: 0,
            },
          ],
        },
        {
          header: '퀴즈',
          content: [
            {
              title: '코딩배우기',
              runtime: '142',
              preview: 0,
            },
            {
              title: '얏호',
              runtime: '1680',
              preview: 0,
            },
            {
              title: '코딩이 뭘까?',
              runtime: '239',
              preview: 0,
            },
            {
              title: '프로그래밍 혁명에 참여하는 방법',
              runtime: '237',
              preview: 0,
            },
          ],
        },
      ],
    }
  },
}
</script>

<style scoped>
  .v-expansion-panel-header{
    border: 1px solid #e0e0e0;
    background-color: #F9F9FB;
  }

</style>