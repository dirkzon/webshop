<template>
  <div>
    <TopBar></TopBar>
    <v-layout justify-center>
      <v-card  class="mx-8 my-8"
               width="50%"
               style="padding: 40px">
        <v-responsive :aspect-ratio="16/7" >
          <v-form  v-model="valid">
            <v-card-title>Edit {{name}}:</v-card-title>
            <v-card-subtitle>Name:</v-card-subtitle>
            <v-text-field
                :rules="requiredFieldRules"
                v-model="name"
                outlined
                :counter="20"
                required
                data-cy="name">
            </v-text-field>
            <v-card-subtitle>Description:</v-card-subtitle>
            <v-textarea
                :rules="descriptionRules"
                v-model="description"
                outlined
                required
                :counter="250"
                data-cy="description">
            </v-textarea>
            <v-card-subtitle>Price </v-card-subtitle>
            <v-text-field
                :rules="requiredNumberRules"
                v-model="price"
                outlined
                aria-required="true"
                prefix="$"
                type="number"
                data-cy="price">
            </v-text-field>
            <v-card-subtitle>Image url </v-card-subtitle>
            <v-text-field
                v-model="image"
                outlined
                aria-required="true"
                :rules="descriptionRules"
                data-cy="image">
            </v-text-field>
            <v-btn
                @click="UpdateProduct"
                :disabled="!valid"
                class="mx-2 my-2"
                color="secondary"
                data-cy="update">
              <v-icon dark>
                save
              </v-icon>
              Save edited product
            </v-btn>
          </v-form>
          <v-divider></v-divider>
          <v-card-title>Remove:</v-card-title>
          <v-btn
              @click="remove_warning = true"
              class="mx-2 my-2"
              color="#f05454"
              data-cy="remove">
            <v-icon dark>
              delete
            </v-icon>
            remove {{ name }}
          </v-btn>
        </v-responsive>
      </v-card>
    </v-layout>
    <div class="text-center">
      <v-dialog
          v-model="remove_warning"
          width="500">
        <v-card>
          <v-card-title class="headline secondary white--text">
            Are you sure?
          </v-card-title>
          <v-card-text>
            If you remove this product all the reviews will also be removed.
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                @click="DeleteProduct"
                color="warning"
                text
                data-cy="confirmRemove">
              Yes, remove {{ name }}
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<script>
import TopBar from "@/components/TopBar";
import productService from "@/services/product-service"
import router from "@/router";
export default {
  name: "EditProduct",
  components: {TopBar},
  data() {
    return {
      name: '',
      description: '',
      price: '',
      image: '',
      id:0,
      rating:0,
      valid: false,
      remove_warning: false,
      requiredFieldRules: [
        v => !!v || 'Field is required', v => v.length <= 25, v => v.length >= 4
      ],
      requiredNumberRules: [
        v => !!v || 'Field is required'
      ],
      descriptionRules: [
        v => !!v || 'Field is required', v => v.length <= 250, v => v.length >= 4
      ],
    }
  },
  async mounted() {
    let product = await productService.getProductById(this.$route.params.id)
    this.id = product.id;
    this.name = product.name;
    this.description = product.description;
    this.price = product.price;
    this.image = product.image.url;
    this.rating = product.rating;
  },
  methods: {
    UpdateProduct: async function () {
      let updatedProduct = JSON.stringify({
        name: this.name,
        description: this.description,
        price: this.price,
        id: this.id,
        rating: this.rating,
        image: {
          url: this.image,
        }
      })
      await productService.updateProductById(this.id, updatedProduct);
      await router.push({name: `product` , params: {id:this.id}})
    },
    DeleteProduct: async function(){
      await productService.removeProductById(this.id);
      await router.push({name: `home`})
    }
  }
}
</script>

<style scoped>

</style>
