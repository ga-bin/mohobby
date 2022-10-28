<template>
  <v-container fluid>
    <v-sheet max-width="2000">
      <v-slide-group class="pa-2">
        <v-slide-item
          v-for="oneClass in collectClassList.data"
          :key="oneClass.index"
        >
          <div class="displayflex">
            <v-card
              class="mx-auto"
              max-width="320px"
              @click="
                $router.push({
                  name: 'moimBoard',
                  params: { boardId: oneClass.classId },
                })
              "
            >
              <v-img
                :src="require(`@/assets/image/class/thumb/1/1.jpg`)"
                height="200px"
              ></v-img>

              <v-card-subtitle v-text="oneClass.className"></v-card-subtitle>
              <v-spacer />
              <v-chip outlined rounded text>
                {{ oneClass.keywordName }}
              </v-chip>
              <span
                style="margin-left: 100px"
                v-text="oneClass.classPrice"
              ></span>
            </v-card>
          </div>
        </v-slide-item>
      </v-slide-group>
    </v-sheet>
  </v-container>
</template>
<script>
export default {
  props: {
    collectClassList: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      show: false,
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {},
};
</script>
<style scoped>
.row {
  width: 100%;
}

.v-card {
  width: 100%;
}

.v-main {
  width: 100%;
}

.team {
  width: 100%;
}

.v-main__wrap {
  width: 100%;
}

.displayflex {
  display: inline-block;
  width: 270px;
  max-width: 100%;
}
</style>
