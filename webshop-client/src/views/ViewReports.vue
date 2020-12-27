<template>
  <div>
    <TopBar></TopBar>
    <div v-bind:key="report.id" v-for="report in reports">
      <Report v-bind:report="report"></Report>
    </div>
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
    let reports = await reportService.getAllReportsForRetailer();
    console.log(reports);
    this.reports = reports;
  }
}
</script>

<style scoped>

</style>
