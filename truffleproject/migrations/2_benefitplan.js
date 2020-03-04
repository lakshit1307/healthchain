const Benefitplan = artifacts.require("Benefitplan");

module.exports = function(deployer) {
  deployer.deploy(Benefitplan);
};
