var clover = new Object();

// JSON: {classes : [{name, id, sl, el,  methods : [{sl, el}, ...]}, ...]}
clover.pageData = {"classes":[{"el":40,"id":0,"methods":[{"el":12,"sc":2,"sl":10},{"el":15,"sc":2,"sl":13},{"el":18,"sc":2,"sl":16},{"el":21,"sc":2,"sl":19},{"el":24,"sc":2,"sl":22},{"el":27,"sc":2,"sl":25},{"el":30,"sc":2,"sl":28},{"el":33,"sc":2,"sl":31},{"el":36,"sc":2,"sl":34},{"el":39,"sc":2,"sl":37}],"name":"EmployeeBean","sl":3}]}

// JSON: {test_ID : {"methods": [ID1, ID2, ID3...], "name" : "testXXX() void"}, ...};
clover.testTargets = {"test_0":{"methods":[{"sl":22},{"sl":25}],"name":"testGetterSetter","pass":true,"statements":[{"sl":23},{"sl":26}]}}

// JSON: { lines : [{tests : [testid1, testid2, testid3, ...]}, ...]};
clover.srcFileLines = [[], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [], [0], [0], [], [0], [0], [], [], [], [], [], [], [], [], [], [], [], [], [], []]
