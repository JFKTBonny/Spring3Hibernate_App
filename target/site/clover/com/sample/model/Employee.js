var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":75,"id":109,"methods":[{"el":37,"sc":2,"sl":35},{"el":41,"sc":2,"sl":39},{"el":45,"sc":2,"sl":43},{"el":49,"sc":2,"sl":47},{"el":53,"sc":2,"sl":51},{"el":57,"sc":2,"sl":55},{"el":61,"sc":2,"sl":59},{"el":65,"sc":2,"sl":63},{"el":69,"sc":2,"sl":67},{"el":73,"sc":2,"sl":71}],"name":"Employee","sl":14}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_1":{"methods":[{"sl":39},{"sl":43},{"sl":47}],"name":"testEmployeeAvailable","pass":true,"statements":[{"sl":40},{"sl":44},{"sl":48}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [1], [1], [], [], [1], [1], [], [], [1], [1], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], []]
