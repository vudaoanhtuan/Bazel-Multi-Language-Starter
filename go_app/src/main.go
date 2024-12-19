package main

import (
	//"context"
	"fmt"
	"io"
	//"net/http"

	//"github.com/golang/protobuf/proto"
	"github.com/grpc-ecosystem/grpc-gateway/v2/runtime"
	"github.com/grpc-ecosystem/grpc-gateway/v2/utilities"
	//"google.golang.org/grpc"
	"google.golang.org/grpc/codes"
	//"google.golang.org/grpc/grpclog"
	"google.golang.org/grpc/status"
	//"github.com/golang/glog"
)

var _ codes.Code
var _ io.Reader
var _ status.Status
var _ = runtime.String
var a_ = utilities.NewDoubleArray

func main() {
	fmt.Println("Hello")
}
