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
                <v-form  v-model="valid">
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
                  <v-btn
                      @click="updateAccount"
                      :disabled="!valid"
                      class="mx-2 my-2"
                      color="secondary"
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
                      If you remove your account all your products and their reviews will also be removed.
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
    <v-card-title>All your products:</v-card-title>
    <v-row align="center"
           justify="center">
      <div v-bind:key="product.id" v-for="product in products">
        <ProductThumbnail v-bind:product="product"></ProductThumbnail>
      </div>
    </v-row>
  </div>
</template>

<script>
import TopBar from "@/components/TopBar";
import retailerService from "@/services/retailer-service";
import ProductThumbnail from "@/components/ProductThumbnail";
import router from "@/router";

export default {
  name: "ViewRetailerAccount",
  components: {TopBar, ProductThumbnail},
  props: ["retailer"],
  data(){
    return{
      avatar: "",
      valid: false,
      username: "",
      password: "",
      email: "",
      products: [],
      remove_warning: false,
      requiredFieldRules: [
        v => !!v || 'Field is required' ,v => v.length <= 25, v => v.length >= 4
      ],
    }
  },
  async mounted() {
    this.retailer = await retailerService.getMe();
    this.username = this.retailer.account.username;
    this.password = this.retailer.account.password;
    this.email = this.retailer.account.email;
    this.avatar = this.retailer.avatar.url;
    this.products = await retailerService.getAllProducts(this.retailer.id);
  },
  methods:{
    updateAccount: async function(){
      let updatedUser = JSON.stringify({
        account: {
          email: this.email,
          password: this.password,
          username: this.username
        },
        avatar: {
          url: this.avatar
        }
      })
      await retailerService.updateRetailerById(updatedUser);
      await window.location.reload();
    },
    removeAccount: async function(){
      await retailerService.removeRetailerById();
      await this.$cookies.remove("access_token");
      await router.push('/login');
    }
  }
}
</script>

<style scoped>
</style>
