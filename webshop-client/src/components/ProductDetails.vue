<template>
  <v-layout justify-center>
      <v-card  class="mx-8 my-8"
               width="50%">
        <v-responsive :aspect-ratio="16/7" >
          <v-form>
            <v-card-title>Product:</v-card-title>
            <v-card-subtitle>Name:</v-card-subtitle>
            <v-text-field
                v-model="returnProduct.name"
                outlined
                :counter="20"
                required
            ></v-text-field>
              <v-card-subtitle>Description:</v-card-subtitle>
            <v-textarea
                v-model="returnProduct.description"
                outlined
                required
                :counter="300">
            </v-textarea>
            <v-card-subtitle>Price </v-card-subtitle>
            <v-text-field
                v-model="returnProduct.price"
                outlined
                aria-required="true"
                prefix="$">
            </v-text-field>
            <v-btn
                @click="ReturnProduct()"
                class="mx-2 my-2"
                color="secondary">
              <v-icon dark>
                save
              </v-icon>
              Save product
            </v-btn>
          </v-form>
        </v-responsive>
      </v-card>
  </v-layout>
</template>

<script>
export default {
  name: "ProductDetails",
  props:["product"],
  data(){
    return{
      returnProduct:{
        name: '',
        price: '',
        description: '',
        category:{
          id: 'category:1'
        },

      }
    }
  },
  watch:{
    product: function (){
      this.returnProduct.name = this.product.name;
      this.returnProduct.description = this.product.description;
      this.returnProduct.price = this.product.price;
    }
  },
  methods:{
    ReturnProduct:function (){
      this.$emit('ReturnProduct', this.returnProduct)
    }
  },
}
</script>

<style scoped>
.v-card{
  padding: 40px;
}
</style>