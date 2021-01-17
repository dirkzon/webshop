<template>
  <div>
    <TopBar></TopBar>
    <v-layout justify-center>
      <v-card  class="mx-8 my-8"
               width="70%"
      style="padding: 40px;
              margin: 20px;">
        <v-responsive :aspect-ratio="15/6">
          <v-row no-gutters>
            <v-responsive :aspect-ratio="14/7" >
              <v-card flat>
                <v-form v-model="valid">
                  <v-card-title>Account:</v-card-title>
                  <v-divider></v-divider>
                  <v-card-subtitle>Name:</v-card-subtitle>
                  <v-text-field
                      v-model="username"
                      :rules="requiredFieldRules"
                      outlined
                      :counter="25"
                      required
                      data-cy="username">
                  </v-text-field>
                  <v-card-subtitle>e-mail:</v-card-subtitle>
                  <v-text-field
                      v-model="email"
                      outlined
                      :counter="25"
                      :rules="requiredFieldRules"
                      required
                      data-cy="email">
                  </v-text-field>
                  <v-card-subtitle>password:</v-card-subtitle>
                  <v-text-field
                      v-model="password"
                      outlined
                      :counter="25"
                      :rules="requiredFieldRules"
                      required
                      data-cy="password">
                  </v-text-field>
                  <v-divider></v-divider>
                  <v-card-title>Address:</v-card-title>
                  <v-card-subtitle>country:</v-card-subtitle>
                  <v-text-field
                      v-model="country"
                      outlined
                      :counter="25"
                      :rules="requiredFieldRules"
                      required
                      data-cy="address">
                  </v-text-field>
                  <v-card-subtitle>street name:</v-card-subtitle>
                  <v-text-field
                      v-model="streetName"
                      outlined
                      :counter="25"
                      :rules="requiredFieldRules"
                      required
                      data-cy="streetName">
                  </v-text-field>
                  <v-card-subtitle>house number:</v-card-subtitle>
                  <v-text-field
                      v-model="houseNumber"
                      type="number"
                      :rules="requiredNumberRules"
                      outlined
                      required
                      data-cy="houseNumber">
                  </v-text-field>
                  <v-btn
                      :disabled="!valid"
                      class="mx-2 my-2"
                      color="secondary"
                      @click="updateAccount"
                      data-cy="update">
                    <v-icon dark>
                      create
                    </v-icon>
                    Update account
                  </v-btn>
                </v-form>
              </v-card>
            </v-responsive>
            <v-spacer></v-spacer>
            <div>
              <v-card-title>Avatar:</v-card-title>
              <v-divider></v-divider>
              <v-card style="margin: 20px; height: min-content">
                <v-img v-bind:src="avatar"
                       :aspect-ratio="1/1"
                       height="400"
                       width="400">
                </v-img>
                <v-file-input
                    v-model="avatar"
                    @change="updateAvatar"
                    accept="image/*"
                    prepend-icon="attach_file"
                ></v-file-input>
              </v-card>
              <v-btn
                  @click="remove_warning = true"
                  class="mx-2 my-2"
                  color="#f05454"
                  data-cy="remove">
                <v-icon dark>
                  delete
                </v-icon>
                remove account
              </v-btn>
              <div class="text-center">
                <v-dialog
                    v-model="remove_warning"
                    width="500">
                  <v-card>
                    <v-card-title class="headline secondary white--text">
                      Are you sure?
                    </v-card-title>
                    <v-card-text>
                      If you remove your account all reviews will also be removed.
                    </v-card-text>
                    <v-divider></v-divider>
                    <v-card-actions>
                      <v-spacer></v-spacer>
                      <v-btn
                          @click="removeAccount"
                          color="warning"
                          text
                          data-cy="confirmRemove">
                        Yes, remove my account
                      </v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </div>
            </div>
          </v-row>
        </v-responsive>
      </v-card>
    </v-layout>
    <v-card-title>All your reviews:</v-card-title>
    <div v-bind:key="review.id" v-for="review in customer.reviews">
      <v-card
          class="mx-8 my-8"
          width="600px">
        <v-responsive :aspect-ratio="16/7" >
          <UserCard v-bind:user="customer"></UserCard>
          <v-divider></v-divider>
          <v-subheader>
            <v-rating :value="review.rating"
                      :size="15"
                      color="warning"
                      background-color="warning"
                      readonly
                      half-increments
                      empty-icon="star_border"
                      full-icon="star"
                      half-icon="star_half"
                      length="5"
            ></v-rating>
            <v-card-text class="text-right">
              {{review.created}}
            </v-card-text>
          </v-subheader>
          <v-card-text class="subtitle-2">
            {{review.body}}
          </v-card-text>
        </v-responsive>
      </v-card>
    </div>
  </div>
</template>

<script>
import TopBar from "@/components/TopBar";
import customerService from "@/services/customer-service"
import UserCard from "@/components/UserCard";
import router from "@/router";

export default {
  name: "ViewCustomerAccount",
  components: {TopBar, UserCard},
  props: ["customer"],
  data(){
    return{
      avatar: "",
      valid: false,
      username: "",
      password: "",
      email: "",
      country:'',
      streetName:'',
      houseNumber:'',
      remove_warning: false,
      requiredFieldRules: [
        v => !!v || 'Field is required' ,v => v.length <= 25, v => v.length >= 4
      ],
      requiredNumberRules: [
        v => !!v || 'Field is required'
      ],
    }
  },
  async mounted() {
    this.customer = await customerService.getMe();
    this.username = this.customer.account.username;
    this.password = this.customer.account.password;
    this.email = this.customer.account.email;
    this.avatar = this.customer.avatar.url;
    this.country = this.customer.address.country;
    this.streetName = this.customer.address.streetName;
    this.houseNumber = this.customer.address.houseNumber;
  },
  methods:{
    updateAvatar: function(event) {
        let reader = new FileReader();
        reader.onload = (e) =>{
          this.avatar = e.target.result
        }
        reader.readAsDataURL(event)
      },
    updateAccount: async function(){
      let updatedUser = JSON.stringify({
        account: {
          email: this.email,
          password: this.password,
          username: this.username
        },
        avatar: {
          url: this.avatar
        },
        address: {
          country: this.country,
          streetName: this.streetName,
          houseNumber: this.houseNumber
        }
      })
      await customerService.updateCustomerById(updatedUser)
      this.customer = await customerService.getMe();
    },
    removeAccount: async function() {
      await customerService.removeCustomerById();
      await this.$cookies.remove("access_token");
      await router.push('/login');
    }
  }
}
</script>

<style scoped>
</style>
