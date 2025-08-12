var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":45,"id":137,"methods":[{"el":28,"sc":2,"sl":20},{"el":32,"sc":2,"sl":30},{"el":36,"sc":2,"sl":34},{"el":40,"sc":2,"sl":38},{"el":44,"sc":2,"sl":42}],"name":"EmployeeServiceImpl","sl":15}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":34},{"sl":42}],"name":"testEmployeeAvailable","pass":true,"statements":[{"sl":35},{"sl":43}]},"test_2":{"methods":[{"sl":34},{"sl":42}],"name":"testEmployeeNotAvailable","pass":true,"statements":[{"sl":35},{"sl":43}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [1, 2], [1, 2], [], [], [], [], [], [], [1, 2], [1, 2], [], []]
