<template>
  <div>
    <TopBar></TopBar>
    <span v-if="reports.length == 0"> No reports.</span>
    <v-col style="width: min-content; padding: 50px">
      <div v-bind:key="report.id" v-for="report in reports">
        <Report v-bind:report="report"></Report>
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
    this.reports = await reportService.getAllReportsForRetailer();
  }
}
</script>

<style scoped>

</style>
