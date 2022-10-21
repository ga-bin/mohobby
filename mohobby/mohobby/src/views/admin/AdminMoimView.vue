<template>
    <main>
        <AdminSidebar></AdminSidebar>
        <div style="margin-left: 60px; width: 1000px">
        <br>
        <h3>신고된 소모임</h3>
        <br>
          <v-data-table
    :headers="headers"
    :items="moimFlagList"
    sort-by="calories"
    class="elevation-1"
  >
    <!-- <template v-slot:top>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
        <v-dialog
          v-model="dialog"
          max-width="500px"
        >
          <template v-slot:activator="{ on, attrs }">
          </template>
          <v-card>
            <v-card-title>
              <span class="text-h5">{{ formTitle }}</span>
            </v-card-title>

            <v-card-text>
              <v-container>
                <v-row>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.name"
                      label="Dessert name"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.calories"
                      label="Calories"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.fat"
                      label="Fat (g)"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.carbs"
                      label="Carbs (g)"
                    ></v-text-field>
                  </v-col>
                  <v-col
                    cols="12"
                    sm="6"
                    md="4"
                  >
                    <v-text-field
                      v-model="editedItem.protein"
                      label="Protein (g)"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </v-container>
            </v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                color="blue darken-1"
                text
                @click="close"
              >
                Cancel
              </v-btn>
              <v-btn
                color="blue darken-1"
                text
                @click="save"
              >
                Save
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-dialog v-model="dialogDelete" max-width="500px">
          <v-card>
            <v-card-title class="text-h5">Are you sure you want to delete this item?</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="blue darken-1" text @click="closeDelete">Cancel</v-btn>
              <v-btn color="blue darken-1" text @click="deleteItemConfirm">OK</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
    </template> -->
    <template v-slot:item.actions="{ item }">
      <v-icon
        small
        class="mr-2"
        @click="editFlagMoim(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
        @click="deleteFlagMoim(item)"
      >
        mdi-delete
      </v-icon>
    </template>   small
     
    <template v-slot:no-data>
      <v-btn
        color="primary"
        @click="initialize"
      >
        Reset
      </v-btn>
    </template>
    <template v-slot:item.showDetail="{ item }">
      <v-icon
        @click="goToMoim(item)"
      >
        mdi-arrow-right-bold-box
      </v-icon>
    </template>   small
     
    <template v-slot:no-data>
      <v-btn
        color="primary"
        @click="initialize"
      >
        Reset
      </v-btn>
    </template>
  </v-data-table>

    </div>
    </main>
</template>
<script>
import AdminSidebar from "../../components/admin/AdminSidebar.vue";
export default {
    name: '',
    components: { AdminSidebar },
    data() {
        return {
dialog: false,
      dialogDelete: false,
      headers: [
        {
          text: '신고번호',
          align: 'start',
          sortable: false,
          value: 'flag_id',
        },
        { text: '신고자', value: 'flagFrom' },
        { text: '신고소모임', value: 'flagTo' },
        { text: '신고코드', value: 'flagCode' },
        { text: '신고이유 (g)', value: 'flagReason' },
        { text: '관리자 승인여부', value: 'adminConfirm' },
        { text: '신고결과', value: 'flagResult' },
        { text: '수정하기', value: 'actions', sortable: false },
        { text: '상세보기', value: 'showDetail', sortable: false },
      ],
      desserts: [],
      editedIndex: -1,
      editedItem: {},
      defaultItem: {
        name: '',
        calories: 0,
        fat: 0,
        carbs: 0,
        protein: 0,
      },
        moimFlagList : [],
    }
},
    beforeCreate() {},
    created() {
      this.getFlagedMoim();
    },
    beforeMount() {},
    mounted() {},
    beforeUpdate() {},
    updated() {},
    beforeUnmount() {},
    unmounted() {},
    computed: {
      formTitle () {
        return this.editedIndex === -1 ? 'New Item' : 'Edit Item'
      },
    },
     watch: {
      dialog (val) {
        val || this.close()
      },
      dialogDelete (val) {
        val || this.closeDelete()
      },
    },

    methods: {
      getFlagedMoim() {
        const vm = this;
          this.axios({
            url: "http://localhost:8088/java/adminflagmoim",
            method: "get",
          })
            .then(function (response) {
              console.log(response);
              for (let i = 0; i < response.data.length; i++) {
                if(response.data[i].adminConfirm == 0) {
                  response.data[i].adminConfirm = "미승인";
                } else if(response.data[i].adminConfirm == 1){
                  response.data[i].adminConfirm = "승인";
                }
                if(response.data[i].flagResult == 0) {
                  response.data[i].flagResult = "통과";
                } else if(response.data[i].flagResult == 1) {
                  response.data[i].flagResult = "패널티";
                }
              }
              vm.moimFlagList = response.data;
            })
            .catch(function (error) {
              console.log(error);
            });
      },
    showFlaggedMoim (event) {
      console.log("clicked");
      // this.editedIndex = this.items.indexOf(item)
      // this.editedItem = Object.assign({}, item)
      // console.log(this.editedIndex)
      // console.log(this.editedItem)
    },
      editFlagMoim (item) {
        this.editedItem = Object.assign({}, item);
        console.log(this.editedItem);

        (async () => {
        const { value: fruit } = await this.$swal.fire({
          title: '소모임 신고 결과를 결정해 주세요',
          input: 'select',
          inputOptions: {
            0: '미승인',
            1: '승인',
          },
          inputPlaceholder: '소모임 신고 결과',
          showCancelButton: true,
          inputValidator: (value) => {
            return new Promise((resolve) => {
              console.log(value);
            })
          }
        })
        if (value) {
          this.axios({
            url: "http://localhost:8088/java/updateflagmoim",
            method: "put",
            data: {
              fragId: value,
              adminConfirm : 1,
              flagId : this.editedItem.flagId
            },
          })
            .then(function (response) {
              console.log("회원정보 수정 성공");
            })
            .catch(function (error) {
              console.log("회원정보 수정 실패");
            });
        } else {

        }
        })()
      },

      deleteFlagMoim (item) {
        this.editedIndex = this.moimFlagList.indexOf(item);
        console.log(this.editedIndex);
        this.editedItem = Object.assign({}, item);
        console.log(this.editedItem);
        console.log(this.editedItem);
        console.log(this.editedItem);
        console.log(this.editedItem);
        console.log(this.editedItem);
      },

      deleteItemConfirm () {
        this.desserts.splice(this.editedIndex, 1)
        this.closeDelete()
      },

      close () {
        this.dialog = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },

      closeDelete () {
        this.dialogDelete = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
      },

      save () {
        if (this.editedIndex > -1) {
          Object.assign(this.desserts[this.editedIndex], this.editedItem)
        } else {
          this.desserts.push(this.editedItem)
        }
        this.close()
      },

      goToMoim(item) {
        this.editedItem = Object.assign({}, item);
        console.log(this.editedItem);
        console.log(this.editedItem.flagTo);
        this.$router.push({
            name: "moimBoard",
            params: { moimId: this.editedItem.flagTo, boardType: 1 },
          });
      }
    },
}
</script>