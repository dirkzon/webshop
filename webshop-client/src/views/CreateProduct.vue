<template>
  <div>
    <TopBar></TopBar>
    <v-layout justify-center>
      <v-card  class="mx-8 my-8"
               width="50%">
        <v-responsive :aspect-ratio="16/7" >
          <v-form  v-model="valid">
            <v-card-title>Create a new product:</v-card-title>
            <v-card-subtitle>Name:</v-card-subtitle>
            <v-text-field
                :rules="requiredFieldRules"
                v-model="name"
                outlined
                :counter="20"
                required
            ></v-text-field>
            <v-card-subtitle>Description:</v-card-subtitle>
            <v-textarea
                :rules="descriptionRules"
                v-model="description"
                outlined
                required
                :counter="250">
            </v-textarea>
            <v-card-subtitle>Price </v-card-subtitle>
            <v-text-field
                :rules="requiredNumberRules"
                v-model="price"
                outlined
                aria-required="true"
                prefix="$"
                type="number">
            </v-text-field>
            <v-card-subtitle>Image url </v-card-subtitle>
            <v-text-field
                v-model="image"
                outlined
                aria-required="true"
                :rules="descriptionRules">
            </v-text-field>
            <v-btn
                @click="saveProduct"
                :disabled="!valid"
                class="mx-2 my-2"
                color="secondary">
              <v-icon dark>
                save
              </v-icon>
              Create product
            </v-btn>
          </v-form>
        </v-responsive>
      </v-card>
    </v-layout>
  </div>
</template>

<script>
import TopBar from "@/components/TopBar";
import retailerService from "@/services/retailer-service"
import router from "@/router";
export default {
  name: "CreateProduct",
  components: {TopBar},
  data() {
    return {
      name: '',
      description: '',
      price: '',
      image: '',
      valid: false,
      requiredFieldRules: [
        v => !!v || 'Field is required' ,v => v.length <= 25, v => v.length >= 4
      ],
      requiredNumberRules: [
        v => !!v || 'Field is required'
      ],
      descriptionRules: [
        v => !!v || 'Field is required' ,v => v.length <= 250, v => v.length >= 4
      ],
    }
  },
  methods:{
    saveProduct: async function(){
      let newProduct = JSON.stringify({
        name: this.name,
        description: this.description,
        price: this.price,
        image: {
          url: this.image,
        }
      })
      let product =  await retailerService.createProduct(newProduct);
      await router.push({name: 'product' , params: {id:product.id}})
    }
  }
}
</script>

<style scoped>
.v-card{
  padding: 40px;
}
</style>
