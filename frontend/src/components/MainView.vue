<template>
  <nav>
    <v-toolbar
        dark
        prominent
        src="https://cdn.vuetifyjs.com/images/backgrounds/vbanner.jpg"
    >
      <div>
        <v-toolbar-title class="ma-2" style="font-size: 32px">Let's Meet!</v-toolbar-title>
      </div>

      <v-spacer></v-spacer>

      <div class="mt-15">
        <v-btn icon @click="edit()">
          <v-icon>person</v-icon>
        </v-btn>
      </div>

      <div class="mt-15" >
        <v-btn icon @click="logOut()">
          <v-icon>exit_to_app</v-icon>
        </v-btn>
      </div>


    </v-toolbar>

    <template>
      <v-data-table
          :headers="headers"
          :items="meets"
          :items-per-page="5"
          class="elevation-1 ma-3"
      >
        <template v-slot:top>
          <v-toolbar
              flat
          >
            <v-toolbar-title>Meets</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-dialog
                v-model="dialog"
                max-width="1000px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                    color="primary"
                    dark
                    class="mb-2"
                    v-bind="attrs"
                    v-on="on"
                >
                  New Meet
                </v-btn>
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
                            label="Meet's name"
                        ></v-text-field>
                      </v-col>
                      <v-col
                          cols="12"
                          sm="6"
                          md="4"
                      >
                        <template>
                          <div>
                            <v-menu
                                ref="menu"
                                v-model="menu"
                                :close-on-content-click="false"
                                transition="scale-transition"
                                offset-y
                                min-width="auto"
                            >
                              <template v-slot:activator="{ on, attrs }">
                                <v-text-field
                                    v-model="editedItem.date"
                                    label="Date"
                                    prepend-icon="mdi-calendar"
                                    readonly
                                    v-bind="attrs"
                                    v-on="on"
                                ></v-text-field>
                              </template>
                              <v-date-picker
                                  v-model="editedItem.date"
                                  :active-picker.sync="activePicker"
                                  :min="new Date().toISOString().substr(0, 10)"
                                  max="2031-01-01"
                                  @change="saveDate"
                              ></v-date-picker>
                            </v-menu>
                          </div>
                        </template>
                      </v-col>
                      <v-col
                          cols="12"
                          sm="6"
                          md="4"
                      >
                        <v-text-field
                            v-model="editedItem.time"
                            label="Time"
                        ></v-text-field>
                      </v-col>

                      <v-col
                          cols="12"
                          sm="12"
                          md="12"
                      >
                        <template>
                          <v-container fluid>
                            <v-row>
                              <v-select
                                  v-model="editedItem.participants"
                                  :items="users"
                                  :item-text="'name'"
                                  :item-value="'email'"
                                  name="selectedCategory"
                                  :menu-props="{ maxHeight: '400' }"
                                  label="Select participants"
                                  multiple
                                  autocomplete
                                  persistent-hint
                              >
                                <template slot="selection" scope="data">
                                  {{data.item.name}} {{data.item.surname}} ({{data.item.email}})
                                </template>
                                <template slot="item" scope="data">
                                  {{data.item.name}} {{data.item.surname}} ({{data.item.email}})
                                </template>

                              </v-select>
                            </v-row>
                          </v-container>
                        </template>
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
            <v-dialog v-model="dialogDelete" max-width="1000px">
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
            <v-dialog v-model="dialogInfo" max-width="500px">
              <v-card>

                <v-card-title class="text-h5">Participants</v-card-title>

                <v-card-text>
                  <div v-for = "(item,index) in participants" :key = "index" style = "color: black; justify-content: left">
                    {{item.name}} {{item.surname}} ({{item.mail}})
                  </div>
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="closeInfo">OK</v-btn>
                  <v-spacer></v-spacer>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>
        <template v-slot:item.actions="{ item }">
          <v-icon
              small
              class="mr-2"
              @click="getInfo(item)"
          >
            info
          </v-icon>
          <v-icon
              small
              class="mr-2"
              @click="editItem(item)"
          >
            edit
          </v-icon>
          <v-icon
              small
              @click="deleteItem(item)"
          >
            delete
          </v-icon>
        </template>
      </v-data-table>
    </template>

  </nav>

</template>

<script>

import {api} from "@/util/Api";
import {auth} from "@/util/Auth";

export default {

  name: "MainView",

  methods: {

    logOut() {
      auth.logout(this);
    },

    edit() {
      this.$router.push("/edit-profile");
    },

    editItem (item) {
      this.editedIndex = this.meets.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialog = true
    },

    deleteItem (item) {
      this.editedIndex = this.meets.indexOf(item)
      this.editedItem = Object.assign({}, item)
      this.dialogDelete = true
    },

    deleteItemConfirm () {
      api.delete(this, "/meet", {id: this.editedItem.id}, () => {

        this.getData ();
      });
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

    closeInfo () {
      this.dialogInfo = false
      this.$nextTick(() => {
        this.editedItem = Object.assign({}, this.defaultItem)
        this.editedIndex = -1
      })
    },

    save () {
      if (this.editedIndex > -1) {
        // this.editedItem.participants = this.participants
        api.put(this, "/meet", this.editedItem, () => {
          this.getData ();
        });
      } else {
        api.post(this, "/meet", this.editedItem, () => {
          this.getData ();
        });
      }
      this.close()
    },

    getData () {
      api.get(this, "/meet/data", {}, data => {
        this.meets = data;
      });
    },

    saveDate (date) {
      this.$refs.menu.save(date)
    },

    getInfo (item) {
      this.editedIndex = this.meets.indexOf(item)
      this.editedItem = Object.assign({}, item)
      api.get(this, "/meet/participants", {id: this.editedItem.id}, data => {
        this.participants = data;
      });
      this.dialogInfo = true
    }

  },

  created() {
    this.getData ();
    api.get(this, "/user/allUsers", {}, data => {
      this.users = data;
    });

  },

  computed: {
    formTitle () {
      return this.editedIndex === -1 ? 'New Meet' : 'Edit Meet'
    },
  },

  watch: {
    dialog (val) {
      val || this.close()
    },
    dialogDelete (val) {
      val || this.closeDelete()
    },
    menu (val) {
      val && setTimeout(() => (this.activePicker = 'YEAR'))
    },
  },

  data () {
    return {
      e6: [],
      e7: [],
      dialog: false,
      dialogDelete: false,
      dialogInfo: false,
      meets: [],
      participants: [{
        name: '',
        surname: '',
        email: '',
      }],
      headers: [
        {
          text: 'Name',
          align: 'start',
          sortable: true,
          value: 'name',
        },
        { text: 'Creator\'s name', value: 'creatorName' },
        { text: 'Creator\'s surname', value: 'creatorSurname' },
        { text: 'Creation date', value: 'creationDate' },
        { text: 'Last modification date', value: 'modificationDate' },
        { text: 'Date', value: 'date' },
        { text: 'Time', value: 'time'},
        { text: 'Actions', value: 'actions', sortable: false },
      ],
      editedIndex: -1,
      editedItem: {
        id: '',
        name: '',
        date: '',
        time: '',
        participants: [],
      },
      defaultItem: {
        id: '',
        name: '',
        date: '',
        time: '',
        participants: [],
      },
      activePicker: null,
      menu: false,
      users: [{
        name: '',
        surname: '',
        email: '',
      }],
    }
  },
}
</script>

<style scoped>
.v-toolbar--prominent:not(.v-toolbar--bottom) .v-toolbar__title {
  align-self: flex-start;
}
</style>