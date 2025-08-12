var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":47,"id":245,"methods":[{"el":24,"sc":2,"sl":21},{"el":33,"sc":2,"sl":26},{"el":46,"sc":2,"sl":35}],"name":"EmployeeServiceImplTest","sl":15}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":35}],"name":"testEmployeeAvailable","pass":true,"statements":[{"sl":37},{"sl":38},{"sl":39},{"sl":40},{"sl":41},{"sl":42},{"sl":43},{"sl":45}]},"test_2":{"methods":[{"sl":26}],"name":"testEmployeeNotAvailable","pass":true,"statements":[{"sl":28},{"sl":29},{"sl":30},{"sl":32}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [2], [], [2], [2], [2], [], [2], [], [], [1], [], [1], [1], [1], [1], [1], [1], [1], [], [1], [], []]
