package com.healthedge.healthchain.blockchain.ContractWrapper;


import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.15.
 */
@SuppressWarnings("rawtypes")
public class BenefitPlan extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50600080546001600160a01b03191633179055610bc3806100326000396000f3fe608060405234801561001057600080fd5b50600436106100575760003560e01c8063047589051461005c5780633332872c14610175578063799a9333146102695780639613320c14610394578063fa099b671461039c575b600080fd5b6101006004803603602081101561007257600080fd5b810190602081018135600160201b81111561008c57600080fd5b82018360208201111561009e57600080fd5b803590602001918460018302840111600160201b831117156100bf57600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610450945050505050565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561013a578181015183820152602001610122565b50505050905090810190601f1680156101675780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6102196004803603602081101561018b57600080fd5b810190602081018135600160201b8111156101a557600080fd5b8201836020820111156101b757600080fd5b803590602001918460018302840111600160201b831117156101d857600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610592945050505050565b60408051602080825283518183015283519192839290830191858101910280838360005b8381101561025557818101518382015260200161023d565b505050509050019250505060405180910390f35b6103926004803603604081101561027f57600080fd5b810190602081018135600160201b81111561029957600080fd5b8201836020820111156102ab57600080fd5b803590602001918460018302840111600160201b831117156102cc57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295949360208101935035915050600160201b81111561031e57600080fd5b82018360208201111561033057600080fd5b803590602001918460018302840111600160201b8311171561035157600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600092019190915250929550610697945050505050565b005b6101006108b5565b610392600480360360408110156103b257600080fd5b6001600160a01b038235169190810190604081016020820135600160201b8111156103dc57600080fd5b8201836020820111156103ee57600080fd5b803590602001918460018302840111600160201b8311171561040f57600080fd5b91908080601f0160208091040260200160405190810160405280939291908181526020018383808284376000920191909152509295506109d7945050505050565b6000546060906001600160a01b031633146104a0576040805162461bcd60e51b815260206004820152600b60248201526a139bdd08105b1b1bddd95960aa1b604482015290519081900360640190fd5b6001826040518082805190602001908083835b602083106104d25780518252601f1990920191602091820191016104b3565b518151600019602094850361010090810a820192831692199390931691909117909252949092019687526040805197889003820188208054601f60026001831615909802909501169590950492830182900482028801820190528187529294509250508301828280156105865780601f1061055b57610100808354040283529160200191610586565b820191906000526020600020905b81548152906001019060200180831161056957829003601f168201915b50505050509050919050565b6000546060906001600160a01b031633146105e2576040805162461bcd60e51b815260206004820152600b60248201526a139bdd08105b1b1bddd95960aa1b604482015290519081900360640190fd5b6003826040518082805190602001908083835b602083106106145780518252601f1990920191602091820191016105f5565b51815160209384036101000a60001901801990921691161790529201948552506040805194859003820185208054808402870184019092528186529350915083018282801561058657602002820191906000526020600020905b81546001600160a01b0316815260019091019060200180831161066e5750505050509050919050565b6000546001600160a01b031633146106e4576040805162461bcd60e51b815260206004820152600b60248201526a139bdd08105b1b1bddd95960aa1b604482015290519081900360640190fd5b6106ec610ae3565b81815260405183518291600191869190819060208401908083835b602083106107265780518252601f199092019160209182019101610707565b51815160209384036101000a600019018019909216911617905292019485525060405193849003810190932084518051919461076794508593500190610af6565b509050507f3baf810bab05b7f0241b303c33ab55f43835686da75be3f5a18d5ad1cf5bbc7a836003856040518082805190602001908083835b602083106107bf5780518252601f1990920191602091820191016107a0565b51815160209384036101000a60001901801990921691161790529201948552506040805194859003820185208186528651918601919091528551909493508392508282019160608401919087019080838360005b8381101561082b578181015183820152602001610813565b50505050905090810190601f1680156108585780820380516001836020036101000a031916815260200191505b5083810382528481815481526020019150805480156108a057602002820191906000526020600020905b81546001600160a01b03168152600190910190602001808311610882575b505094505050505060405180910390a1505050565b6060600160026000336001600160a01b03166001600160a01b0316815260200190815260200160002060405180828054600181600116156101000203166002900480156109395780601f10610917576101008083540402835291820191610939565b820191906000526020600020905b815481529060010190602001808311610925575b505092835250506040805160209281900383018120805460026001821615610100026000190190911604601f810185900485028301850190935282825290929091908301828280156109cc5780601f106109a1576101008083540402835291602001916109cc565b820191906000526020600020905b8154815290600101906020018083116109af57829003601f168201915b505050505090505b90565b6000546001600160a01b03163314610a24576040805162461bcd60e51b815260206004820152600b60248201526a139bdd08105b1b1bddd95960aa1b604482015290519081900360640190fd5b6001600160a01b03821660009081526002602090815260409091208251610a4d92840190610af6565b506003816040518082805190602001908083835b60208310610a805780518252601f199092019160209182019101610a61565b51815160209384036101000a60001901801990921691161790529201948552506040519384900381019093208054600181018255600091825293902090920180546001600160a01b0319166001600160a01b039590951694909417909355505050565b6040518060200160405280606081525090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610b3757805160ff1916838001178555610b64565b82800160010185558215610b64579182015b82811115610b64578251825591602001919060010190610b49565b50610b70929150610b74565b5090565b6109d491905b80821115610b705760008155600101610b7a56fea265627a7a72315820142c87969977fd31bb32d2243adb85c317b11a4289558845a497770ba9262cc864736f6c63430005100032";

    public static final String FUNC_ASSIGNMEMBERTOBENEFITPLAN = "assignMemberToBenefitPlan";

    public static final String FUNC_GETALLMEMBERSFORBENFITPLAN = "getAllMembersForBenfitPlan";

    public static final String FUNC_GETBENEFITPLAN = "getBenefitPlan";

    public static final String FUNC_GETBENEFITPLANFORMEMBER = "getBenefitPlanForMember";

    public static final String FUNC_SETBENEFITPLAN = "setBenefitPlan";

    public static final Event BENEFITPLANUPDATED_EVENT = new Event("benefitPlanUpdated",
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<DynamicArray<Address>>() {}));
    ;

    @Deprecated
    protected BenefitPlan(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BenefitPlan(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected BenefitPlan(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected BenefitPlan(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    protected BenefitPlan(String contractBinary, String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider gasProvider) {
        super(contractBinary, contractAddress, web3j, transactionManager, gasProvider);
    }

    public List<BenefitPlanUpdatedEventResponse> getBenefitPlanUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(BENEFITPLANUPDATED_EVENT, transactionReceipt);
        ArrayList<BenefitPlanUpdatedEventResponse> responses = new ArrayList<BenefitPlanUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BenefitPlanUpdatedEventResponse typedResponse = new BenefitPlanUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.benfitPlanId = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.memberAdresses = (List<String>) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BenefitPlanUpdatedEventResponse> benefitPlanUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, BenefitPlanUpdatedEventResponse>() {
            @Override
            public BenefitPlanUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BENEFITPLANUPDATED_EVENT, log);
                BenefitPlanUpdatedEventResponse typedResponse = new BenefitPlanUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.benfitPlanId = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.memberAdresses = (List<String>) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<BenefitPlanUpdatedEventResponse> benefitPlanUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BENEFITPLANUPDATED_EVENT));
        return benefitPlanUpdatedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> assignMemberToBenefitPlan(String _memberId, String _benefitPlanId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ASSIGNMEMBERTOBENEFITPLAN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _memberId),
                        new org.web3j.abi.datatypes.Utf8String(_benefitPlanId)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getAllMembersForBenfitPlan(String _benefitPlanId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETALLMEMBERSFORBENFITPLAN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_benefitPlanId)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<String> getBenefitPlan(String _id) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETBENEFITPLAN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_id)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> getBenefitPlanForMember() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETBENEFITPLANFORMEMBER,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setBenefitPlan(String _id, String _ipfsHash) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETBENEFITPLAN,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_id),
                        new org.web3j.abi.datatypes.Utf8String(_ipfsHash)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static BenefitPlan load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BenefitPlan(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static BenefitPlan load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BenefitPlan(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static BenefitPlan load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new BenefitPlan(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static BenefitPlan load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new BenefitPlan(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<BenefitPlan> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BenefitPlan.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<BenefitPlan> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BenefitPlan.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BenefitPlan> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BenefitPlan.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<BenefitPlan> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BenefitPlan.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class BenefitPlanUpdatedEventResponse extends BaseEventResponse {
        public String benfitPlanId;

        public List<String> memberAdresses;
    }
}
