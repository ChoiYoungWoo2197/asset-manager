<template>
  <div>

  </div>
</template>

<script>
import XLSX from 'xlsx';

export default {
  name: "FileDownload",
  data() {
    return {
      data1: [
        {
          mode: 1,
          affinity: 0.5,
          lb: 1.34,
          ub: 2.53,
          pUrl: "purl1",
          qUrl: "qurl1"
        },
        {
          mode: 2,
          affinity: 1.5,
          lb: 13.34,
          ub: 2.453,
          pUrl: "purl2",
          qUrl: "qurl2"
        }
      ]
    }
  },
  methods: {
    download(data) {
      console.log(data)
      const workBook = XLSX.utils.book_new();
      const workSheet = XLSX.utils.json_to_sheet(data.fileData);
      XLSX.utils.book_append_sheet(workBook, workSheet, data.fileName);
      XLSX.writeFile(workBook, data.fileName+'.xlsx');
    }
  }
}
</script>

<style scoped>

</style>