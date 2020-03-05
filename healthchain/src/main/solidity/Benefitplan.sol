pragma solidity >=0.4.22 <0.6.0;

contract Benefitplan{
    
    //the provider address
    address private provider ;

    //structure for benefit plan
    struct benefitPlan{
        //file hash of the benefitPlan
        string ipfsHash;
    }

   //String is the benefitPlan id; mapping of benefit plan id to benefit plan
    mapping(string => benefitPlan) private benefitPlanMap;
     
    //map of member id to benefit plan id
    mapping(address => string) private memberBenefitMap;
     
    //map of member id to benefit plan id
    mapping(string => address[]) membersForBenifitPlanMap;
     
    constructor() public {
        provider=msg.sender;
    }
     /***  Methods **********************************/
    /// @dev Add benefitPlan to the benefitPlanMap map
    /// @param _id benefit plan id
    /// @param _ipfsHash The hash of json stored in ipfs
    function setBenefitPlan(string memory _id, string memory _ipfsHash) public onlyProvider(){
             benefitPlan memory c;
             c.ipfsHash=_ipfsHash;
            benefitPlanMap[_id]=c;
            emit benefitPlanUpdated(_id, membersForBenifitPlanMap[_id]);
    }
    
    //Events to be emitted after a benefit plan is added or changed
    event benefitPlanUpdated(string benfitPlanId, address[] memberAdresses);
    
    //assign a member to a benefit plan
    function assignMemberToBenefitPlan(address _memberId, string memory _benefitPlanId) public onlyProvider() {
        memberBenefitMap[_memberId]=_benefitPlanId;
        membersForBenifitPlanMap[_benefitPlanId].push(_memberId);
    }
    
    //get a list of members for a benefit plan
    function getAllMembersForBenfitPlan(string memory _benefitPlanId) public onlyProvider() view returns (address[] memory) {
        return(membersForBenifitPlanMap[_benefitPlanId]);
    }
    
    //get a benefit plan for a member
    function getBenefitPlan(string memory _id) public  onlyProvider() view returns (string memory){
        return(benefitPlanMap[_id].ipfsHash);
    }
    
    //get benfit plan for member
    function getBenefitPlanForMember() public view returns (string memory){
        return(benefitPlanMap[memberBenefitMap[msg.sender]].ipfsHash);
    }
    
    //modifier to check if the calling entity is the provider
    modifier onlyProvider() {
        require(provider==msg.sender,"Not Allowed");
        _;
    }

}
