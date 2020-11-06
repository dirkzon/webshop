<template>
  <div>
    <TopBar></TopBar>
    <ProductDrawer></ProductDrawer>
    <product-list v-bind:products="products"></product-list>
  </div>
</template>

<script>
import TopBar from "@/components/TopBar";
import axios from "axios";
import ProductList from "@/components/ProductList";
import ProductDrawer from "@/components/ProductDrawer";

export default {
  name: "SearchProducts",
  components: {
    ProductList,
    TopBar,
    ProductDrawer
  },
  data(){
    return{
      products: Object
    }
  },
  watch:{
    $route(){
      this.getProducts()
    }
  },
  mounted() {
    this.getProducts()
  },
  methods:{
    getProducts: function () {
      axios
          .get("http://localhost:4545/v1/products/browse",{
            params: this.axiosParams
          })
          .then(response => (this.products = response.data))
          .catch(error => alert(error))
    }
  },
  computed: {
    axiosParams() {
      const params = new URLSearchParams();
      params.append('query', this.$route.query.query);
      return params;
    }
  }
}
</script>
