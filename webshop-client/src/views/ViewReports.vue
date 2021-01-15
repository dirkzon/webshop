<template>
  <div>
    <TopBar></TopBar>
    <span v-if="reports.length == 0"> No reports.</span>
    <v-col style="width: min-content; padding: 50px">
      <div v-bind:key="report.id" v-for="report in reports">
        <Report v-bind:report="report" @remove="removeReview" @dismiss="dismissReport"></Report>
        <v-divider></v-divider>
      </div>
    </v-col>
  </div>
</template>

<script>
import TopBar from "@/components/TopBar";
import Report from "@/components/Report";

import reportService from "@/services/report-service"

export default {
  name: "ViewReports",
  components: {Report, TopBar},
  data() {
    return {
      reports: [],
    }
  },
  async mounted() {
    await this.getReports();
  },
  methods:{
    getReports: async function(){
      this.reports = await reportService.getAllReportsForRetailer();
    },
    removeReview: async function(id){
      await reportService.removeReview(id);
      await this.getReports();
    },
    dismissReport: async function(id){
      await reportService.dismissReport(id);
      await this.getReports();
    }
  }
}
</script>

<style scoped>

</style>
