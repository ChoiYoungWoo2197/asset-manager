<template>
  <!-- Modal -->
  <div class="modal fade" id="file-upload-modal" tabindex="-1" aria-labelledby="authority-create-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header ">
          <h2 class="tit_step">파일업로드</h2>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-lg">
              <div class="card card-primary card-outline">
                <div class="card-body">
                  <form>
                    <div class="custom-file">
                      <!--accept=".conf, .xls, .xlsx"-->
                      <input type="file" class="custom-file-input" id="fileUpload" @change="readFile" :key="fileInputKey"
                             accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
                      <label class="custom-file-label" for="fileUpload">파일선택</label>
                    </div>
                    <span class="text-primary">※ 엑셀 형식의 파일만 지원합니다.</span>
                  </form>
                </div>
              </div>
            </div>
          </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">  <i class="far fa-window-close pr-1"></i>취소</button>
          <!--          <button id="createBtn" type="button" class="btn btn-secondary" @click="createBtnClick"> <i class="far fa-edit pr-1"></i>등록</button>-->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import XLSX from 'xlsx';

export default {
  name: "FileUpload",
  data() {
    return {
      datas : [],
      fileInputKey : 0,
    }
  },
  methods: {
    clearData() {
      $("#fileUpload").next().html("");
      this.fileInputKey++;
      this.datas = [];
    },
    showFileUploadModal() {
      $('#file-upload-modal').modal("show");
    },
    readFile(event) {
      if (! event.target.files.length) return;

      const file = event.target.files[0];
      let reader = new FileReader();
      let tmpResult = {};
      $("#fileUpload").next().html(file.name);
      reader.onload = () => {
        let data = reader.result;
        let workbook = XLSX.read(data, {type: 'binary'});
        workbook.SheetNames.forEach(sheetName => {
/*          workbook.Sheets[sheetName].A1.w = "test1";
          workbook.Sheets[sheetName].B1.w = "test2";
          workbook.Sheets[sheetName].C1.w = "test3";
          workbook.Sheets[sheetName].D1.w = "test4";
          console.log(workbook.Sheets[sheetName], 'dsfdsf');*/

          const roa = XLSX.utils.sheet_to_json(workbook.Sheets[sheetName]);
          tmpResult = roa;
        });
        this.datas=tmpResult;
        if(this.datas.length > 0) {
          this.$emit("completeFileUpload", this.datas);
        }
      };
      $('#file-upload-modal').modal("hide");
      reader.readAsBinaryString(file);
    }
  }

}
</script>

<style scoped>

</style>