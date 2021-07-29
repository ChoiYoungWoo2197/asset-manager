<template>
  <!-- Modal 수정 -->
  <div class="modal fade" id="partner-company-update-modal" tabindex="-1" aria-labelledby="partner-company-update-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-xl modal-dialog-scrollable ">
      <div class="modal-content ">
        <div class="modal-header">
          <h2 class="tit_step">업체수정</h2>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-lg-7">
              <div class="card card-primary card-outline">
                <div class="card-body">
                  <form>
                    <div class="form-group">
                      <label for="nameEdit">업체명<span class="text-danger">*</span></label>
                      <input type="text" class="form-control" id="nameEdit" placeholder="" :value="name">
                    </div>
                    <div class="form-group">
                      <label for="codeEdit">업체코드<span class="text-danger">*</span></label>
                      <div class="form-group row">
                        <div class="col-sm">
                          <input type="text" class="form-control " id="codeEdit" placeholder="" :value="code" readonly="readonly" disabled>
                        </div>
                      </div>
                    </div>
                    <div class="form-group">
                      <label for="typeEdit">구분<span class="text-danger">*</span></label>
                      <select class="form-control " style="width: 100%;" id="typeEdit" disabled readonly="readonly">
                        <option v-for="(type, index) in types"
                                :key="index"
                                :value="type.code"
                                :selected="index === 0">
                          {{ type.name }}
                        </option>
                      </select>
                    </div>
                    <div class="form-group">
                      <label for="companyPhoneEdit">전화번호<span class="text-danger"></span></label>
                      <input type="text" class="form-control " id="companyPhoneEdit" placeholder="" :value="companyPhone">
                    </div>
                    <div class="form-group">
                      <label for="remarkEdit">비고<code></code></label>
                      <textarea class="form-control" id="remarkEdit" :value="remark"></textarea>
                    </div>
                    <div class="form-group">
                      <div class="custom-control custom-radio d-inline pr-1">
                        <input class="custom-control-input" type="radio" id="useYnFalseEditModal" name="useYnRadio" @click="clickRadioBtn(false)">
                        <label for="useYnFalseEditModal" class="custom-control-label">비활성화</label>
                      </div>
                      <div class="custom-control custom-radio d-inline">
                        <input class="custom-control-input" type="radio" id="useYnTrueEditModal" name="useYnRadio" checked="" @click="clickRadioBtn(true)">
                        <label for="useYnTrueEditModal" class="custom-control-label">활성화</label>
                      </div>
                    </div>
                  </form>
                </div>
              </div>
            </div>
            <div class="col-lg">
              <PartnerCompanyMemberManager ref="partnerCompanyMemberManager" :p-datas="activePartnerCompanyMembers"></PartnerCompanyMemberManager>
            </div>
          </div>

        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">  <i class="far fa-window-close pr-1"></i>취소</button>
          <button type="button" class="btn btn-primary" @click="updateBtnClick"> <i class="far fa-edit pr-1"></i>수정</button>
          <button type="button" class="btn btn-danger" @click="deleteBtnClick"><i class="far fa-trash-alt pr-1"></i>삭제</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import axios from "axios";
import PartnerCompanyMemberManager from "@/components/PartnerCompany/PartnerCompanyMemberManager.vue";

export default {
  name: "PartnerCompanyUpdate",
  components: {
    PartnerCompanyMemberManager
  },
  data() {
    return {
      id : null,
      name : null,
      code : null,
      companyPhone : null,
      remark : null,
      useYn : null,
      types: [
        {code : 'buy', name : '구매'},
        {code : 'rental', name : '렌탈'},
      ],
      activePartnerCompanyMembers : [],
    }
  },
  methods: {
    setData(data) {
      this.id = data.id;
      this.name = data.name;
      this.code = data.code;
      this.companyPhone = data.companyPhone;
      this.remark = data.remark;
      this.useYn = data.useYn;

      $('#partner-company-update-modal #typeEdit').val(data.type);
      if(this.useYn === true) {
        $('#partner-company-update-modal #useYnTrueEditModal').prop("checked", true);
      } else {
        $('#partner-company-update-modal #useYnFalseEditModal').prop("checked", true);
      }

      if(this.$refs.partnerCompanyMemberManager !== null) {
        this.$refs.partnerCompanyMemberManager.clearData(true);
      }
      this.findPartnerCompanyMembers();
    },
    updateBtnClick() {
      const vm = this;

      if($( '#partner-company-update-modal input#nameEdit' ).val() ==="") {
        alert("이름을 입력해주세요.");
        return false;
      }

      axios.put('http://localhost:8080/api/partner-companys/' + this.id, {
        name : $( '#partner-company-update-modal input#nameEdit' ).val(),
        companyPhone : $( '#partner-company-update-modal input#companyPhoneEdit' ).val(),
        remark : $( '#partner-company-update-modal textarea#remarkEdit' ).val(),
        useYn : this.useYn,
      }).then(response => {
        // console.log(response);
        if(response.status === 200) {
          // this.$router.push('Authority');
          vm.updateAndDeletePartnerCompanyMembers();
          vm.$emit("updateData", response.data);
          $('#partner-company-update-modal').modal("hide");
        }
      }).catch(e => {
        alert(e);
      })
    },
    deleteBtnClick() {
      const vm = this;

      if(!confirm(this.name + "을 삭제하시겠습니까?")) {
        return false;
      }

      axios.delete('http://localhost:8080/api/partner-companys/' + this.id)
          .then(response => {
            // console.log(response);
            if(response.status === 200) {
              // this.$router.push('Authority');
              vm.$emit("updateData", response.data);
              $('#partner-company-update-modal ').modal("hide");
            }
          }).catch(e => {
        alert(e);
      })
    },
    clickRadioBtn(flag){
      this.useYn = flag;
    },
    findPartnerCompanyMembers() {
      const vm = this;
      axios.get('http://localhost:8080/api/partner-companys/' + vm.id + "/members", {
      }).then(response => {
        vm.activePartnerCompanyMembers = response.data.filter(partnerCompanyMember => partnerCompanyMember.useYn === true);
      }).catch(e => {
        alert(e);
      })
    },
    updatePartnerCompanyMembers() {
      const vm = this;
      let members = this.$refs.partnerCompanyMemberManager.getDatas().slice();
      members.forEach(member => {
        member.parentId = vm.id;
      });
      return axios.put('http://localhost:8080/api/partner-company-members' , members);
    },
    deletePartnerCompanyMembers() {
      const vm = this;
      let members = this.$refs.partnerCompanyMemberManager.getRemoveDatas().slice();
      members.forEach(member => {
        member.parentId = vm.id;
        member.useYn = false;
      });

      return axios.put('http://localhost:8080/api/partner-company-members' , members);
      // return axios.delete('http://localhost:8080/api/partner-company-members', member);
      // return axios.get('http://localhost:8080/api/partner-company-members');
    },
    updateAndDeletePartnerCompanyMembers() {
      axios.all([this.updatePartnerCompanyMembers(), this.deletePartnerCompanyMembers()])
          .then(axios.spread(function (updateResult, deleteResult) {
            console.log(updateResult, deleteResult);
          })).catch(e => {
        alert(e);
      });
    }

  }
}
</script>

<style scoped>

</style>